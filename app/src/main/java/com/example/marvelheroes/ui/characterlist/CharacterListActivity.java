package com.example.marvelheroes.ui.characterlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.marvelheroes.R;
import com.example.marvelheroes.data.local.db.AppDatabase;
import com.example.marvelheroes.databinding.ActivityCharacterListBinding;
import com.example.marvelheroes.dto.Character;
import com.example.marvelheroes.ui.MainViewModel;
import com.example.marvelheroes.ui.characterDetails.CharacterDetailsActivity;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharacterListActivity extends AppCompatActivity implements OnCharacterDetails, TextWatcher {

    private static final String TAG = "MainActivity";

    private ActivityCharacterListBinding binding;

    private final CharacterListAdapter characterListAdapter = new CharacterListAdapter(this);

    private MainViewModel viewModel;

    @Inject
    AppDatabase appDatabase;

    private PopupMenu popupMenu;

    private enum Filter {
        ALL, BOOKMARKED
    }

    private Filter currentFilter = Filter.ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_list);
        binding.rvCharacters.setAdapter(characterListAdapter);

        appDatabase.characterDao().getAllCharacters();

        binding.edSearch.addTextChangedListener(this);

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                binding.swDarkTheme.setChecked(false);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                binding.swDarkTheme.setChecked(true);
                break;
        }

        binding.swDarkTheme.setOnCheckedChangeListener((compoundButton, checked) -> {
            if (checked) {
                // change to dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                // change to light mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });


        popupMenu = new PopupMenu(this,binding.tvFilter);
        popupMenu.getMenuInflater().inflate(R.menu.filter_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.filterAll:
                    currentFilter = Filter.ALL;
                    binding.tvFilter.setText("Filter By : All");
                    viewModel.getCharacters(false);
                    break;
                case R.id.filterBookmarked:
                    currentFilter = Filter.BOOKMARKED;
                    binding.tvFilter.setText("Filter By : Bookmarked");
                    viewModel.getBookmarkedCharacters();
                    break;
            }
            return false;
        });

        binding.tvFilter.setOnClickListener(view -> {
            popupMenu.show();
        });


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setupObservers();

        binding.swRefresh.setOnRefreshListener(() -> {
            if (currentFilter == Filter.BOOKMARKED) {
                viewModel.getBookmarkedCharacters();
            } else {
                viewModel.getCharacters(true);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentFilter == Filter.BOOKMARKED) {
            viewModel.getBookmarkedCharacters();
        } else {
            viewModel.getCharacters(false);
        }
    }

    private void setupObservers() {

        viewModel.observeCharacter().observe(this, characters -> {
            if (binding.swRefresh.isRefreshing())
                binding.swRefresh.setRefreshing(false);
            if (!characters.isEmpty())
                characterListAdapter.setCharacters(characters);
        });

        viewModel.observeErrors().observe(this, errorMsg -> {
            if (binding.swRefresh.isRefreshing())
                binding.swRefresh.setRefreshing(false);

            if (errorMsg != null || !errorMsg.isEmpty()) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(Character character) {
        Gson gson = new Gson();
        Intent characterDetailsIntent = new Intent(this, CharacterDetailsActivity.class);
        String characterJson = gson.toJson(character);
        characterDetailsIntent.putExtra("character", characterJson);

        startActivity(characterDetailsIntent);
    }

//    @Override
//    public void onBookmark(Character character, int position) {
//        viewModel.bookmarkCharacter(character);
//        characterListAdapter.notifyItemChanged(position);
//    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        viewModel.searchCharacter(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}