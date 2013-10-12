package sy.XO_company.xo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean X_turn = true;
	private ImageButton[] gridButtons = new ImageButton[9];
	private Button rest_button;
	private int[][] state = new int[3][3];
	boolean win = false;
	Drawable xIcon, oIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// /
		rest_button = (Button) findViewById(R.id.button10);
		gridButtons[0] = (ImageButton) findViewById(R.id.button2);
		gridButtons[1] = (ImageButton) findViewById(R.id.button1);
		gridButtons[2] = (ImageButton) findViewById(R.id.button3);
		gridButtons[3] = (ImageButton) findViewById(R.id.button4);
		gridButtons[4] = (ImageButton) findViewById(R.id.button5);
		gridButtons[5] = (ImageButton) findViewById(R.id.button6);
		gridButtons[6] = (ImageButton) findViewById(R.id.button7);
		gridButtons[7] = (ImageButton) findViewById(R.id.button8);
		gridButtons[8] = (ImageButton) findViewById(R.id.button9);
		//
		xIcon = Drawable.createFromPath("assets\\Picture1.png");
		oIcon = Drawable.createFromPath("assets\\Picture2.png");

		rest_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++)
						state[i][j] = 0;
				for (int i = 0; i < 9; i++)
					gridButtons[i].setImageURI(Uri.parse("@drawable/xicon"));
				win = false;
				X_turn = true;
			}
		});
		for (int i = 0; i < 9; i++) {
			// gridButtons[i] = (Button) findViewById(R.id.button3 + i);

			gridButtons[i].setOnClickListener(new View.OnClickListener() {

				@SuppressLint("NewApi")
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageButton source = (ImageButton) arg0;
					int index = 0;
					for (int j = 0; j < 9; j++) {
						if (source == gridButtons[j]) {
							index = j;
							break;
						}
					}
					int raw = index / 3;
					int column = index % 3;

					if (state[raw][column] == 0 && !win) {
						if (X_turn) {
							ImageButton hh=(ImageButton)(findViewById(R.id.imageButton2));
							source.setBackground(hh.getBackground());
							source.setImageBitmap(null);
							//source.setImageURI(Uri.parse("@drawable/xicon"));
							//source.setBackgroundDrawable(xIcon);
						} else {
							ImageButton ii=(ImageButton)(findViewById(R.id.imageButton1));
							source.setBackground(ii.getBackground());
							source.setImageBitmap(null);
							//source.setImageURI(Uri.parse("@drawable/oicon"));
							//source.setBackgroundDrawable(xIcon);
						}
						// check Winning

						state[raw][column] = X_turn ? 1 : -1;
						// Toast.makeText(MainActivity.this, "you chose " +index
						// +",with " + state[raw][column], 0).show();
						win = checkWinning(index);
						if (win) {
							Toast.makeText(MainActivity.this,
									(X_turn ? "X " : "O ") + "Wins", 0).show();
						}
						X_turn = !X_turn;
					}
					if (!win) {
						boolean draw = checkDraw();
						if (draw) {
							Toast.makeText(MainActivity.this, "Draw", 0).show();
						}
					}
				}
			});
		}
	}

	public boolean checkDraw() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (state[i][j] == 0)
					return false;
			}
		return true;
	}

	public boolean checkWinning(int index) {
		int raw = index / 3;
		int column = index % 3;
		int sumRaw = 0, sumColumn = 0, primaryDiam = 0, secondaryDia = 0;
		for (int i = 0; i < 3; i++) {
			sumRaw += state[raw][i];
			sumColumn += state[i][column];
			primaryDiam += state[i][i];
			secondaryDia += state[i][3 - i - 1];
		}
		if (3 == Math.abs(sumRaw) || 3 == Math.abs(sumColumn)
				|| 3 == Math.abs(primaryDiam) || 3 == Math.abs(secondaryDia))
			return true;
		else
			return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
