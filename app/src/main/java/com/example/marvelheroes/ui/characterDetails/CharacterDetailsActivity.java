package com.example.marvelheroes.ui.characterDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.marvelheroes.R;
import com.example.marvelheroes.databinding.ActivityCharacterDetailsBinding;
import com.example.marvelheroes.models.Url;
import com.example.marvelheroes.ui.MainViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.marvelheroes.dto.Character;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharacterDetailsActivity extends AppCompatActivity {

    private ActivityCharacterDetailsBinding binding;

    private Character character;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_details);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        String characterJson = getIntent().getStringExtra("character");
        if (characterJson != null) {
            Gson gson = new Gson();
            TypeToken<Character> token = TypeToken.get(Character.class);
            character = gson.fromJson(characterJson, token.getType());
            setupUi(character);
        }



        binding.imgBack.setOnClickListener(view -> {
            finish();
        });

        binding.imgBookmark.setOnClickListener(view -> {
            viewModel.bookmarkCharacter(character);
            binding.imgBookmark.setImageResource(character.getBookmarked() ? R.drawable.ic_bookmark : R.drawable.ic_bookmark_idle);
        });


    }

    @SuppressLint("ResourceAsColor")
    private void setupUi(Character character) {

        Glide.with(binding.imgCharacter).load(character.getThumbnail()).error(R.drawable.ic_broken_image).into(binding
                .imgCharacter);

        binding.tvName.setText(character.getName());

        binding.tvDesc.setText(character.getDescription().isEmpty() ? "No Description Found" : character.getDescription());

        binding.imgBookmark.setImageResource(character.getBookmarked() ? R.drawable.ic_bookmark : R.drawable.ic_bookmark_idle);

        StringBuilder comicsString = new StringBuilder();
        AtomicInteger counter = new AtomicInteger(1);
        character.getComics().subList(0, Math.min(character.getComics().size(), 5)).forEach(item -> {
            comicsString.append(counter.getAndIncrement() + ". " + item.getName() + "\n");
        });

        binding.tvComics.setText(comicsString.toString().isEmpty() ? "No Comics Found" : comicsString);

        StringBuilder seriesString = new StringBuilder();
        counter.set(1);

        character.getSeries().subList(0, Math.min(character.getSeries().size(), 5)).forEach(s -> {
            seriesString.append(counter.getAndIncrement() + ". " + s.getName() + "\n");
        });


        binding.tvSeries.setText(seriesString.toString().isEmpty() ? "No Series Found" : seriesString);

        StringBuilder eventsString = new StringBuilder();
        counter.set(1);

        character.getEvents().subList(0, Math.min(character.getEvents().size(), 5)).forEach(item -> {
            eventsString.append(counter.getAndIncrement() + ". " + item.getName() + "\n");
        });

        binding.tvEvents.setText(eventsString.toString().isEmpty() ? "No Events Found" : eventsString);

        counter.set(1);
        List<Url> urls = character.getUrls();

        for (Url url : urls) {
            TextView tv = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(params);
            tv.setText(counter.getAndIncrement() + ". " + url.getUrl());
            tv.setTextSize(16);
            tv.setTextAppearance(R.style.TextAppearance_MarvelHeroes_Body1);
            tv.setTextColor(ResourcesCompat.getColor(getResources(),R.color.link_color,null));
            tv.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.getUrl()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            });

            binding.llUrls.addView(tv);
        }
    }
}