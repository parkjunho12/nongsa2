package com.example.nongsa2;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private TextView mTextMessage;
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.container2);
    Button imageButton2,imageButton3;
    Button imageButton1,homebtn;
    LinearLayout linearLayout;
    static int p =0;
    static int v=1;
    private ViewPager viewPager;
    private imagepagerAdapter imagepagerAdapter;
    Thread thread =null;
    Handler handler = null;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new Fragment();
                    imageButton1.setBackgroundColor(0xffffffff);
                    imageButton1.setTextColor(getResources().getColor(R.color.black));
                    imageButton2.setBackgroundColor(0xffffffff);
                    imageButton2.setTextColor(getResources().getColor(R.color.black));
                    imageButton3.setBackgroundColor(0xffffffff);
                    imageButton3.setTextColor(getResources().getColor(R.color.black));
                    homebtn.setBackgroundColor(getResources().getColor(R.color.back));
                    homebtn.setTextColor(0xffffffff);
                   replaceFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                  //  fragment = new secondpage();
                    Intent mainIntent = null;
                    if (FirebaseAuth.getInstance().getCurrentUser()==null) {
                        mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                    } else {
                        mainIntent = new Intent(MainActivity.this, ChatlistActivity.class);
                    }
                    MainActivity.this.startActivity(mainIntent);

                   // replaceFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    Intent mainIntent2 = null;
                    Log.e("@@@@@@@@@@@@@@@@@@!","@@@@@@@@@@@@@@@@@@!"+Static_setting.ID);
                    Log.e("@@@@@@@@@@@@@@@@@@!","@@@@@@@@@@@@@@@@@@!!"+Static_setting.PW);
                    Log.e("@@@@@@@@@@@@@@@@@@!","@@@@@@@@@@@@@@@@@@!!"+Static_setting.Name);
                    Log.e("@@@@@@@@@@@@@@@@@@!","@@@@@@@@@@@@@@@@@@!!"+Static_setting.Phone);
                    if (FirebaseAuth.getInstance().getCurrentUser()==null) {
                        mainIntent2 = new Intent(MainActivity.this, LoginActivity.class);
                    } else {
                        mainIntent2 = new Intent(MainActivity.this, Mypage1.class);
                    }
                    MainActivity.this.startActivity(mainIntent2);
                    return true;
            }
            if(fragment!=null) {
                Log.d("ddd","setfm");
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.container2, fragment).commit();
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // String test = FirebaseInstanceId.getInstance().getToken();
       // Log.d("Token Value", test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        linearLayout = (LinearLayout)findViewById(R.id.linbtn);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment fragment = new Fragment();
                imageButton1.setBackgroundColor(0xffffffff);
                imageButton1.setTextColor(getResources().getColor(R.color.black));
                imageButton2.setBackgroundColor(0xffffffff);
                imageButton2.setTextColor(getResources().getColor(R.color.black));
                imageButton3.setBackgroundColor(0xffffffff);
                imageButton3.setTextColor(getResources().getColor(R.color.black));
                homebtn.setBackgroundColor(getResources().getColor(R.color.back));
                homebtn.setTextColor(0xffffffff);
                replaceFragment(fragment);
            }
        });
        imageButton1 = (Button) findViewById(R.id.imbtn1);
        imageButton2 = (Button) findViewById(R.id.garden);
        imageButton3 = (Button) findViewById(R.id.consultation);
        homebtn = (Button) findViewById(R.id.home);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        homebtn.setOnClickListener(this);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ImageButton first =(ImageButton)findViewById(R.id.first);
        ImageButton second =(ImageButton)findViewById(R.id.second);
        ImageButton third =(ImageButton)findViewById(R.id.third);
        ImageButton four =(ImageButton)findViewById(R.id.four);

        imagepagerAdapter = new imagepagerAdapter(getSupportFragmentManager());
        if (FirebaseAuth.getInstance().getCurrentUser()==null) {
        } else {
            new BackgroundTask().execute();
        }



        viewPager.setAdapter(imagepagerAdapter);
        handler = new Handler(){



            public void handleMessage(android.os.Message msg) {

                if(p==0){

                    viewPager.setCurrentItem(1);

                    p++;

                    v=1;

                }else if(p==1){

                    viewPager.setCurrentItem(2);

                    p++;

                }else if(p==2){

                    viewPager.setCurrentItem(0);

                    p=0;

                }

            }

        };
        thread = new Thread(){
            //run은 jvm이 쓰레드를 채택하면, 해당 쓰레드의 run메서드를 수행한다.
            public void run() {
                super.run();
                while(true){
                    try {
                        Thread.sleep(4000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }

        };

        thread.start();


    }

    private void checkLogin(String ID) {

    }


    public static Fragment fa,fb,fc;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view)
    {
        Fragment fragment = null;
        fragmentManager = getSupportFragmentManager();

        switch (view.getId())
        {

            case R.id.home:
                fragment = new Fragment();

                imageButton1.setBackgroundColor(0xffffffff);
                imageButton1.setTextColor(getResources().getColor(R.color.black));
                imageButton2.setBackgroundColor(0xffffffff);
                imageButton2.setTextColor(getResources().getColor(R.color.black));
                imageButton3.setBackgroundColor(0xffffffff);
                imageButton3.setTextColor(getResources().getColor(R.color.black));
                homebtn.setBackgroundColor(getResources().getColor(R.color.back));
                homebtn.setTextColor(0xffffffff);
                replaceFragment(fragment);

//                if(fa != null) hideFragment(fa);
//                if(fb != null) hideFragment(fb);
//                if(fc != null) hideFragment(fc);
                break;
            case R.id.imbtn1:
                fragment = new Gyesi();
                homebtn.setBackgroundColor(0xffffffff);
                homebtn.setTextColor(getResources().getColor(R.color.black));
                imageButton2.setBackgroundColor(0xffffffff);
                imageButton2.setTextColor(getResources().getColor(R.color.black));
                imageButton3.setBackgroundColor(0xffffffff);
                imageButton3.setTextColor(getResources().getColor(R.color.black));
                imageButton1.setBackgroundColor(getResources().getColor(R.color.back));
                imageButton1.setTextColor(0xffffffff);
                replaceFragment(fragment);

//                if(fa == null) {
//                    fa = new Gyesi();
//                   // fragmentManager.beginTransaction().replace(R.id.container2, fa).commit();
//                    addFragment(fa);
//                }
//
//                if(fa != null) showFragment(fa);
//                if(fb != null) hideFragment(fb);
//                if(fc != null) hideFragment(fc);
//                Log.d("ddd","MonthFragment");
                break;
            case R.id.garden:
                fragment = new Garden_board();
                homebtn.setBackgroundColor(0xffffffff);
                homebtn.setTextColor(getResources().getColor(R.color.black));
                imageButton1.setBackgroundColor(0xffffffff);
                imageButton1.setTextColor(getResources().getColor(R.color.black));
                imageButton3.setBackgroundColor(0xffffffff);
                imageButton3.setTextColor(getResources().getColor(R.color.black));
                imageButton2.setBackgroundColor(getResources().getColor(R.color.back));
                imageButton2.setTextColor(0xffffffff);
                replaceFragment(fragment);

//                if(fb == null) {
//                    fb = new Garden_board();
//                    addFragment(fb);
//                }
//
//                if(fa != null) hideFragment(fa);
//                if(fb != null) showFragment(fb);
//                if(fc != null) hideFragment(fc);
//                Log.d("ddd","MonthFragment");
                break;
            case R.id.consultation:
                fragment = new Consultation();
                homebtn.setBackgroundColor(0xffffffff);
                homebtn.setTextColor(getResources().getColor(R.color.black));
                imageButton2.setBackgroundColor(0xffffffff);
                imageButton2.setTextColor(getResources().getColor(R.color.black));
                imageButton1.setBackgroundColor(0xffffffff);
                imageButton1.setTextColor(getResources().getColor(R.color.black));
                imageButton3.setBackgroundColor(getResources().getColor(R.color.back));
                imageButton3.setTextColor(0xffffffff);
                replaceFragment(fragment);

//                if(fc == null) {
//                    fc = new Consultation();
//                    addFragment(fc);
//                }
//
//                if(fa != null) hideFragment(fa);
//                if(fb != null) hideFragment(fb);
//                if(fc != null) showFragment(fc);
//                Log.d("ddd","MonthFragment");
                break;

            case R.id.first:
                Uri uri = Uri.parse("http://returnfarm.com/cmn/sym/mnu/mpm/1030601/htmlMenuView.do");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

                break;
            case R.id.second:

                Uri uri2 = Uri.parse("http://returnfarm.com/cmn/sym/mnu/mpm/1030401/htmlMenuView.do");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case R.id.third:
                Uri uri3 = Uri.parse("http://returnfarm.com/cmn/sym/mnu/mpm/1030101/htmlMenuView.do");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);

                break;
            case R.id.four:
                Uri uri4 = Uri.parse("http://returnfarm.com/cmn/sym/mnu/mpm/1030301/htmlMenuView.do");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);

                break;
        }
    }



    // 뒤로가기 버튼 입력시간이 담길 long 객체
    private long pressedTime = 0;

    // 리스너 생성
    public interface OnBackPressedListener {
        public void onBack();
    }    // 리스너 객체 생성
    private OnBackPressedListener mBackListener;

    // 리스너 설정 메소드
    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }

    // 뒤로가기 버튼을 눌렀을 때의 오버라이드 메소드
    @Override
    public void onBackPressed() {

        // 다른 Fragment 에서 리스너를 설정했을 때 처리됩니다.
        if(mBackListener != null) {
            mBackListener.onBack();
            Log.e("!!!", "Listener is not null");
            // 리스너가 설정되지 않은 상태(예를들어 메인Fragment)라면
            // 뒤로가기 버튼을 연속적으로 두번 눌렀을 때 앱이 종료됩니다.
        } else {
            Log.e("!!!", "Listener is null");
            if ( pressedTime == 0 ) {
                Snackbar.make(findViewById(R.id.container2),
                        " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
            }
            else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);

                if ( seconds > 2000 ) {
                    Snackbar.make(findViewById(R.id.container2),
                            " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                    pressedTime = 0 ;
                }
                else {
                    super.onBackPressed();
                    Log.e("!!!", "onBackPressed : finish, killProcess");
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }
    }
    private void addFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.container2, fragment);
        fragmentTransaction.commit();
    }
    private void hideFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }
    private void showFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container2, fragment);
        fragmentTransaction.commit();
    }



    class BackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String res) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e(this.getClass().getName(), "트라이문out");
                    try {
                        Log.e(this.getClass().getName(), "try in!");
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        Log.e(this.getClass().getName(), "success!" + success);
                        if (success) {
                            Log.e(this.getClass().getName(), "성공함!");
                            Log.e(this.getClass().getName(), "jsonResponse!" + jsonResponse);
                            String ID = jsonResponse.getString("ID");
                            String PW = jsonResponse.getString("PW");
                            String Name = jsonResponse.getString("Name");
                            String Phone = jsonResponse.getString("Phone");

                            Static_setting.ID = ID;
                            Static_setting.PW = PW;
                            Static_setting.Name = Name;
                            Static_setting.Phone = Phone;

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            CheckRequest CheckRequest = new CheckRequest(FirebaseAuth.getInstance().getCurrentUser().getEmail(), responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(CheckRequest);
        }

    }
}

