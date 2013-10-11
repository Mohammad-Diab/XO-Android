package my.example.xvso;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private boolean X_turn  = true;
	private Button[] gridButtons = new Button[9];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//
		for (int i = 0; i < 9; i++) {
			gridButtons[i] = (Button) findViewById(R.id.button1 + i);

			gridButtons[i].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Button source = (Button)arg0;
					if(X_turn)
					{
						source.setText("X");
					}
					else
					{
						source.setText("O");
					}
					X_turn = !X_turn;
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}