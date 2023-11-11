package com.example.actividadapi2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.actividadapi2.R;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.LanguageServiceSettings;
import com.google.cloud.language.v1.Sentiment;

import java.io.InputStream;


public class SentimentAnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment_analysis);

        final EditText inputText = findViewById(R.id.inputText);
        Button analyzeButton = findViewById(R.id.analyzeButton);
        final TextView resultView = findViewById(R.id.resultView);

        analyzeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analyzeSentiment(inputText.getText().toString(), resultView);
            }
        });

        Button backToSecondButton = findViewById(R.id.backToSecondButton);
        backToSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SentimentAnalysisActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void analyzeSentiment(String text, TextView resultView) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Cargar las credenciales desde el archivo JSON en assets
                    try (InputStream credentialsStream = getAssets().open("archivo.json")){
                        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
                        // Crear el cliente de la API
                        LanguageServiceSettings languageServiceSettings = LanguageServiceSettings.newBuilder()
                                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                                .build();

                        // Crear el cliente de la API con las credenciales
                        try (LanguageServiceClient language = LanguageServiceClient.create(languageServiceSettings)) {
                            Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
                            Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

                            // Actualizar la UI en el hilo principal
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String resultText = "Score: " + sentiment.getScore()+ " Magnitud:  " + sentiment.getMagnitude();
                                    resultView.setText(resultText);

                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
