package com.example.marvelheroes.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelheroes.data.local.CharacterLocalRepository;
import com.example.marvelheroes.data.remote.CharacterRemoteRepository;
import com.example.marvelheroes.dto.Character;
import com.example.marvelheroes.models.CharacterResponse;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AppRepositoryImpl implements AppRepository {


    private final CharacterRemoteRepository remoteRepository;
    private final CharacterLocalRepository localRepository;

    private final MediatorLiveData<List<Character>> characters = new MediatorLiveData<>();

    private final MutableLiveData<String> errorMsg = new MutableLiveData<>();

    @Inject
    public AppRepositoryImpl(CharacterRemoteRepository remoteRepository, CharacterLocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public void getAllCharacters(boolean shouldRefresh) {
        if (shouldRefresh) {
            LiveData<Response<CharacterResponse>> source = LiveDataReactiveStreams.fromPublisher(
                    remoteRepository.getCharacters(null)
                            .subscribeOn(Schedulers.io())
            );

            characters.addSource(source, characterResponseResponse -> {
                if (characterResponseResponse.isSuccessful()) {
                    List<Character> result = characterResponseResponse.body().toDto();
                    addAllCharacters(result);
                } else {
                    errorMsg.setValue("Something Went Wrong");
                }
                characters.removeSource(source);
            });
        }

        LiveData<List<Character>> localSource = LiveDataReactiveStreams.fromPublisher(
                localRepository.getAllCharacters()
        );

        characters.addSource(localSource, list -> {
            if (list.isEmpty()) {
                // no data in local
                getAllCharacters(true);
            } else {
                characters.setValue(list);
            }
            characters.removeSource(localSource);

        });
    }

    @Override
    public void updateCharacter(Character character) {
        localRepository.updateCharacter(character).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void addCharacter(Character character) {
        localRepository.addCharacter(character);
    }

    @Override
    public void addAllCharacters(List<Character> characters) {
        localRepository.addAllCharacters(characters);
    }

    @Override
    public void searchCharacter(String searchText) {
        if (searchText.isEmpty()) {
            getAllCharacters(true);
        } else {
            // search locally
            LiveData<List<Character>> localSource = LiveDataReactiveStreams.fromPublisher(
                    localRepository.getAllCharacters()
            );


            characters.addSource(localSource, list -> {
                if (list.isEmpty()) {
                    // no data in local
                    LiveData<Response<CharacterResponse>> remoteSource = LiveDataReactiveStreams.fromPublisher(
                            remoteRepository.getCharacters(searchText)
                                    .subscribeOn(Schedulers.io()));

                    characters.addSource(remoteSource, characterResponseResponse -> {
                        if (characterResponseResponse.isSuccessful()) {
                            List<Character> result = characterResponseResponse.body().toDto();
                            characters.setValue(result);
                            addAllCharacters(result);
                        } else {
                            errorMsg.setValue("Something Went Wrong.");
                        }
                        characters.removeSource(remoteSource);
                    });
                } else {
                    List<Character> filtered = list.stream().filter(character -> character.getName().toLowerCase(Locale.ROOT).contains(searchText.toLowerCase(Locale.ROOT))).collect(Collectors.toList());

                    // not found in local
                    if (filtered.isEmpty()) {
                        LiveData<Response<CharacterResponse>> remoteSource = LiveDataReactiveStreams.fromPublisher(
                                remoteRepository.getCharacters(searchText)
                                        .subscribeOn(Schedulers.io()));

                        characters.addSource(remoteSource, characterResponseResponse -> {
                            if (characterResponseResponse.isSuccessful()) {
                                List<Character> result = characterResponseResponse.body().toDto();
                                characters.setValue(result);
                                addAllCharacters(result);
                            } else {
                                errorMsg.setValue("Something Went Wrong.");
                            }
                            characters.removeSource(remoteSource);
                        });
                    } else {
                        characters.setValue(filtered);
                    }
                    characters.removeSource(localSource);
                }
            });

        }
    }

    @Override
    public void getCharacterById(String characterId) {

    }

    @Override
    public void getBookmarkedCharacters() {
        LiveData<List<Character>> localSource = LiveDataReactiveStreams.fromPublisher(
                localRepository.getBookmarkedCharacters()
        );

        characters.addSource(localSource, bookmarked -> {
            characters.setValue(bookmarked);
            characters.removeSource(localSource);
        });
    }

    @Override
    public void bookmarkCharacter(Character character) {
        character.setBookmarked(!character.getBookmarked());
        updateCharacter(character);
    }

    @Override
    public LiveData<List<Character>> observeCharacters() {
        return characters;
    }

    @Override
    public LiveData<String> observeErrors() {
        return errorMsg;
    }


}
