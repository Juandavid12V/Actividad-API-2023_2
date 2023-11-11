

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

      @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String username = getIntent().getStringExtra("USERNAME");
        String password = getIntent().getStringExtra("PASSWORD");

         TextView usernameTextView = findViewById(R.id.usernameTextView);
        TextView passwordTextView = findViewById(R.id.passwordTextView);

         usernameTextView.setText("usuarioo: " + username);
        passwordTextView.setText("contraseña: " + password);

        Button backToMainButton = findViewById(R.id.backToMainButton);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button toThirdActivityButton = findViewById(R.id.toThirdActivityButton);
        toThirdActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, SentimentAnalysisActivity.class);
                startActivity(intent);
            }
        });


    }
}