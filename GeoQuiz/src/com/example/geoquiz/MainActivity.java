package com.example.geoquiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	
	private static final String TAG = "MainActivity";
	
	private void updateQuestion(){
		int question = mQuestions[mQuestionIndex].getQuestion();
		mQuestionTextView.setText(question);
	}
	
	private void checkAnswer(boolean trueAnswer){
		int mMessageText;
		boolean mTrueAnswer = mQuestions[mQuestionIndex].isTrueQuestion();
		if(trueAnswer == mTrueAnswer){
			mMessageText = R.string.correct_toast;
		} else{
			mMessageText = R.string.incorrect_toast;
		}
		Toast.makeText(this, mMessageText, Toast.LENGTH_SHORT).show();
	}

	private int mQuestionIndex = 0;
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mPrevButton;
	private Button mNextButton;
	
	private TextView mQuestionTextView;
	
	
	
	private TrueFalse[] mQuestions = new TrueFalse[]{
			new TrueFalse(R.string.question1, true),
			new TrueFalse(R.string.question2, true),
			new TrueFalse(R.string.question3, true),
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreat(Bundle) called");
		setContentView(R.layout.activity_main);
		
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mQuestionIndex = (mQuestionIndex + 1) % mQuestions.length;
				updateQuestion();
			}
		});
		
		mTrueButton = (Button)findViewById(R.id.true_button);
		
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkAnswer(true);
				
			}
		});
		
		mFalseButton = (Button)findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkAnswer(false);
				
			}
		});
		
		mPrevButton = (Button)findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mQuestionIndex == 0){
					Toast.makeText(MainActivity.this, R.string.already_first, Toast.LENGTH_SHORT).show();
				} else{
					mQuestionIndex -= 1;
					updateQuestion();
				}
				
			}
		});
		
		mNextButton = (Button)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mQuestionIndex = (mQuestionIndex + 1) % mQuestions.length;
				updateQuestion();
			}
		});
		
		updateQuestion();
	}
	
	@Override
	public void onStart(){
		super.onStart();
		Log.d(TAG, "onStart() called");
	}
	@Override
	public void onPause(){
		super.onPause();
		Log.d(TAG, "onPause() called");
	}
	@Override
	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume() called");
	}
	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "onStop() called");
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
