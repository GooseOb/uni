package com.example.androidfinalproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.Manifest;

public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private Button addButton, reverseButton, clearButton;
    private ListView listView;
    private ArrayList<Map<String, String>> listItems;
    private SimpleAdapter adapter;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "AppPrefs";
    private static final String LIST_KEY = "ListItems";
    private static final String CHANNEL_ID = "PauseNotificationChannel";
    private int pauseCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
        }
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        addButton = findViewById(R.id.addButton);
        reverseButton = findViewById(R.id.reverseButton);
        clearButton = findViewById(R.id.clearButton);
        listView = findViewById(R.id.listView);

        listItems = new ArrayList<>();
        adapter = new SimpleAdapter(this, listItems, android.R.layout.simple_list_item_2,
                new String[] {"item", "subitem"}, new int[] {android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadSavedList();

        addButton.setOnClickListener(v -> {
            String text1 = editText1.getText().toString().trim();
            String text2 = editText2.getText().toString().trim();

            if (text1.isEmpty() || text2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Both fields must be filled!", Toast.LENGTH_SHORT).show();
            } else {
                Map<String, String> item = new HashMap<>();
                item.put("item", text1);
                item.put("subitem", text2);
                listItems.add(item);
                adapter.notifyDataSetChanged();
                saveList();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = listItems.get(position).get("item");
            String subitem = listItems.get(position).get("subitem");
            Toast.makeText(MainActivity.this, item + "\n" + subitem, Toast.LENGTH_SHORT).show();
        });

        reverseButton.setOnClickListener(v -> {
            String text1 = editText1.getText().toString().trim();
            String text2 = editText2.getText().toString().trim();

            if (text1.isEmpty() || text2.isEmpty()) {
                Toast.makeText(MainActivity.this, "Both fields must be filled!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, ReverseActivity.class);
                intent.putExtra("word1", text1);
                intent.putExtra("word2", text2);
                startActivity(intent);
            }
        });

        clearButton.setOnClickListener(v -> {
            listItems.clear();
            adapter.notifyDataSetChanged();
            saveList();
        });

        createNotificationChannel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ++pauseCounter;
        showPauseNotification();
    }

    private void saveList() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = new HashSet<>();
        for (Map<String, String> item : listItems) {
            set.add(item.get("item").replaceAll("-", "\\\\-") + " - " + item.get("subitem").replaceAll("-", "\\\\-"));
        }
        editor.putStringSet(LIST_KEY, set);
        editor.apply();
    }

    private void loadSavedList() {
        Set<String> set = sharedPreferences.getStringSet(LIST_KEY, new HashSet<>());
        for (String entry : set) {
            String[] parts = entry.split(" - ");
            if (parts.length == 2) {
                Map<String, String> item = new HashMap<>();
                item.put("item", parts[0].replaceAll("\\\\-", "-"));
                item.put("subitem", parts[1].replaceAll("\\\\-", "-"));
                listItems.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void showPauseNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("App Paused")
                .setContentText("Pause count: " + pauseCounter)
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        notificationManager.notify(313, notification);
    }

    private void createNotificationChannel() {
        CharSequence name = "Pause Notification Channel";
        String description = "Channel for pause notifications";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
