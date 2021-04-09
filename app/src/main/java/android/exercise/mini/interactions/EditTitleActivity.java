package android.exercise.mini.interactions;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

  private boolean isEditing = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_title);

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    // setup - start from static title with "edit" button
    fabStartEdit.setVisibility(View.VISIBLE);
    fabEditDone.setVisibility(View.GONE);
    textViewTitle.setText("Page title here");
    textViewTitle.setVisibility(View.VISIBLE);
    editTextTitle.setText("Page title here");
    editTextTitle.setVisibility(View.GONE);

    // handle clicks on "start edit"
    fabStartEdit.setOnClickListener(v -> {
      fabStartEdit.animate()
              .alpha(0f)
              .setInterpolator(new AccelerateInterpolator())
              .setDuration(300L)
              .withEndAction(new Runnable() {
                @Override
                public void run() {
                  fabEditDone.setVisibility(View.VISIBLE);
                  fabEditDone.setAlpha(0f);
                  fabStartEdit.setVisibility(View.GONE);
                  fabEditDone.animate()
                          .alpha(1f)
                          .setStartDelay(100L)
                          .setInterpolator(new AccelerateInterpolator())
                          .setDuration(400L)
                          .start();
                }
              })
              .start();

      textViewTitle.setVisibility(View.GONE);
      String textTitle = textViewTitle.getText().toString();
      editTextTitle.setText(textTitle);
      editTextTitle.setVisibility(View.VISIBLE);
      this.isEditing = true;
    });

    // handle clicks on "done edit"
    fabEditDone.setOnClickListener(v -> {

      fabEditDone.animate()
              .alpha(0f)
              .setInterpolator(new AccelerateInterpolator())
              .setDuration(300L)
              .withEndAction(new Runnable() {
                @Override
                public void run() {
                  fabStartEdit.setVisibility(View.VISIBLE);
                  fabStartEdit.setAlpha(0f);
                  fabEditDone.setVisibility(View.GONE);
                  fabStartEdit.animate()
                          .alpha(1f)
                          .setStartDelay(100L)
                          .setInterpolator(new AccelerateInterpolator())
                          .setDuration(400L)
                          .start();
                }
              })
              .start();

      String textTitle = editTextTitle.getText().toString();
      textViewTitle.setText(textTitle);
      textViewTitle.setVisibility(View.VISIBLE);
      editTextTitle.setVisibility(View.GONE);
      this.isEditing = false;
    });
  }

  @Override
  public void onBackPressed() {
    // BACK button was clicked

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    if (this.isEditing){
      editTextTitle.setVisibility(View.GONE);
      textViewTitle.setVisibility(View.VISIBLE);

      fabEditDone.animate()
              .alpha(0f)
              .setInterpolator(new AccelerateInterpolator())
              .setDuration(300L)
              .withEndAction(new Runnable() {
                @Override
                public void run() {
                  fabStartEdit.setVisibility(View.VISIBLE);
                  fabStartEdit.setAlpha(0f);
                  fabEditDone.setVisibility(View.GONE);
                  fabStartEdit.animate()
                          .alpha(1f)
                          .setStartDelay(100L)
                          .setInterpolator(new AccelerateInterpolator())
                          .setDuration(400L)
                          .start();
                }
              })
              .start();
      this.isEditing = false;
    }
    else{
      super.onBackPressed();
    }
  }
}