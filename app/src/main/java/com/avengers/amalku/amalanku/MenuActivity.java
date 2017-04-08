package com.avengers.amalku.amalanku;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MenuActivity extends AppCompatActivity {

    private static String username = "user";
    private static String formattedDate;
    private boolean refresh = false;
    private static int day_, month_, year_;
    private int dateEditText = 0;
    private int year, month, day;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean("dark_theme", false);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        }
        setContentView(R.layout.activity_menu);

        Bundle b = getIntent().getExtras();
        if(b != null){
            username = b.getString("username");
            refresh = b.getBoolean("refresh");
        }

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if(refresh){
            mViewPager.setCurrentItem(3);
            username = "Username";
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda yakin ingin keluar?")
                .setTitle("Konfirmasi")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(1);
                        dialog.dismiss();
                        // CONFIRM
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        // CANCEL
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alert =  builder.create();
        alert.show();
    }

    public void editTarget(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(MenuActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);;
        builder.setView(input);
        builder.setMessage("Kuantitas")
                .setTitle("Edit")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        // CONFIRM
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        // CANCEL
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alert =  builder.create();
        alert.show();
    }

    public void openChart(View view){
        Intent i = new Intent(MenuActivity.this, ChartActivity.class);
        startActivity(i);;
    }

    public void goSubmit(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda yakin?")
                .setTitle("Konfirmasi")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        // CONFIRM
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        // CANCEL
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog alert =  builder.create();
        alert.show();
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Pilih Tangal", Toast.LENGTH_SHORT)
                .show();
        dateEditText = 1;
    }

    @SuppressWarnings("deprecation")
    public void setDate2(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Pilih Tangal", Toast.LENGTH_SHORT)
                .show();
        dateEditText = 2;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            day_ = arg1;
            month_ = arg2+1;
            year_ = arg3;
            if(dateEditText == 1){
                PlaceholderFragment.showDate();
            }
            else{
                PlaceholderFragment.showDate2();
            }
        }
    };

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        Model[] modelItems;
        ModelTarget[] modelItems2;


        public String[] menuItems = {"Edit Profile", "Notifications", "Themes", "About", "Exit"};
        public Integer[] imageId = {
                R.drawable.user,
                R.drawable.notif,
                R.drawable.theme,
                R.drawable.info,
                R.drawable.exit,
        };

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static View rootView3;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
                TextView tv1 = (TextView) rootView.findViewById(R.id.textView);
                tv1.setText(MenuActivity.username);
                TextView tv2 = (TextView) rootView.findViewById(R.id.textView2);
                tv2.setText(MenuActivity.formattedDate);

                modelItems = new Model[10];
                modelItems[0] = new Model("Sholat Subuh", 0);
                modelItems[1] = new Model("Sholat Dzuhur", 0);
                modelItems[2] = new Model("Sholat Ashar", 0);
                modelItems[3] = new Model("Sholat Maghrib", 0);
                modelItems[4] = new Model("Sholat Isya", 0);
                modelItems[5] = new Model("Sholat Dhuha", 0);
                modelItems[6] = new Model("Sholat Tahajjud", 0);
                modelItems[7] = new Model("Sholat Rawatib", 0);
                modelItems[8] = new Model("Tilawah Al-Quran", 0);
                modelItems[9] = new Model("Sedekah", 0);

                CustomAdapter adapter = new CustomAdapter(getActivity(), modelItems);
                ListView listView = (ListView) rootView.findViewById(R.id.listViewCheck);
                listView.setAdapter(adapter);

                return rootView;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                View rootView2 = inflater.inflate(R.layout.fragment_target_page, container, false);
                modelItems2 = new ModelTarget[10];
                modelItems2[0] = new ModelTarget("Sholat Subuh", 1, "Kali", R.drawable.ic_edit_black_24dp);
                modelItems2[1] = new ModelTarget("Sholat Dzuhur", 1, "Kali", R.drawable.ic_edit_black_24dp);
                modelItems2[2] = new ModelTarget("Sholat Ashar", 1, "Kali", R.drawable.ic_edit_black_24dp);
                modelItems2[3] = new ModelTarget("Sholat Maghrib", 1, "Kali", R.drawable.ic_edit_black_24dp);
                modelItems2[4] = new ModelTarget("Sholat Isya", 1, "Kali", R.drawable.ic_edit_black_24dp);
                modelItems2[5] = new ModelTarget("Sholat Dhuha", 4, "Rakaat", R.drawable.ic_edit_black_24dp);
                modelItems2[6] = new ModelTarget("Sholat Tahajjud", 8, "Rakaat", R.drawable.ic_edit_black_24dp);
                modelItems2[7] = new ModelTarget("Sholat Rawatib", 10, "Rakaat", R.drawable.ic_edit_black_24dp);
                modelItems2[8] = new ModelTarget("Tilawah Al-Quran", 3, "Lembar", R.drawable.ic_edit_black_24dp);
                modelItems2[9] = new ModelTarget("Sedekah", 1000, "Rupiah", R.drawable.ic_edit_black_24dp);

                CustomAdapterTarget adapter = new CustomAdapterTarget(getActivity(), modelItems2);
                ListView listView = (ListView) rootView2.findViewById(R.id.listTarget);
                listView.setAdapter(adapter);

                return rootView2;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                rootView3 = inflater.inflate(R.layout.fragment_report_page, container, false);
                return rootView3;
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==4){
                View rootView4 = inflater.inflate(R.layout.fragment_setting_page, container, false);

                CustomList adapter = new
                        CustomList(getActivity(), menuItems, imageId);
                ListView listView = (ListView) rootView4.findViewById(R.id.mainMenu);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position==0){
                            Intent i = new Intent(getActivity(), UserActivity.class);
                            Bundle b = new Bundle();
                            b.putString("username", username);
                            i.putExtras(b);
                            startActivity(i);;
                        }
                        else if(position==1){
                            Intent i = new Intent(getActivity(), NotifActivity.class);
                            startActivity(i);;
                        }
                        else if(position==2){
                            Intent i = new Intent(getActivity(), ThemeActivity.class);
                            startActivity(i);;
                            getActivity().finish();
                        }
                        else if(position==3){
                            Intent i = new Intent(getActivity(), AboutActivity.class);
                            startActivity(i);;
                        }
                        else if(position==4){
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                        }
                        else{
                            Toast.makeText(getActivity(), menuItems[+ position], Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                return rootView4;
            }
            else{
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }
        }

        private static void showDate() {
            TextView dateView = (TextView) rootView3.findViewById(R.id.editText4);
            dateView.setText(new StringBuilder().append(MenuActivity.day_).append("/")
                    .append(MenuActivity.month_).append("/").append(MenuActivity.year_));
        }

        private static void showDate2() {
            TextView dateView = (TextView) rootView3.findViewById(R.id.editText5);
            dateView.setText(new StringBuilder().append(MenuActivity.day_).append("/")
                    .append(MenuActivity.month_).append("/").append(MenuActivity.year_));
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return new SettingPage().newInstance("Halo", "Hai");
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        Drawable myDrawable;;

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "TARGET";
                case 2:
                    return "RAPOR";
                case 3:
                    return "SETTING";
            }
            return null;
        }
    }
}