package com.example.marvelheroes.ui.characterlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marvelheroes.R;
import com.example.marvelheroes.databinding.ItemCharacterBinding;
import com.example.marvelheroes.dto.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private List<Character> characters = new ArrayList<>();

    private OnCharacterDetails characterDetails;

    public CharacterListAdapter(OnCharacterDetails onCharacterDetails) {
        this.characterDetails = onCharacterDetails;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(inflater, parent, false);
        return new CharacterViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bind(characters.get(position),characterDetails);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public void setCharacters(List<Character> characters) {
        if (characters == null) return;
        this.characters = characters;
        notifyDataSetChanged();
    }
}

class CharacterViewHolder extends RecyclerView.ViewHolder {

    private ItemCharacterBinding binding;

    public CharacterViewHolder(@NonNull View itemView, ItemCharacterBinding binding) {
        super(itemView);
        this.binding = binding;
    }

    public void bind(Character character, OnCharacterDetails characterDetails) {
        binding.tvCharacterName.setText(character.getName());

//        binding.imgBookmark.setImageResource(character.getBookmarked() ? R.drawable.ic_bookmark : R.drawable.ic_bookmark_idle);

        Glide.with(binding.imgCharacter)
                .load(character.getThumbnail())
                .error(R.drawable.ic_broken_image)
                .into(binding.imgCharacter);

        binding.getRoot().setOnClickListener(view -> {
            characterDetails.onClick(character);
        });

//        binding.imgBookmark.setOnClickListener(view -> {
//            characterDetails.onBookmark(character,getAdapterPosition());
//        });
    }
}
