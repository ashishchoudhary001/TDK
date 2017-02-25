package com.thekadesikhaana;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;


/**
 * Created by ParmeshMahore on 2/22/17.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = DatePickerFragment.class.getSimpleName();

    public interface UpdateDateInterface {
        void onDateSelected(String str);
    }

    UpdateDateInterface mInterface;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        mInterface =  (SignInActivity) getActivity();
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d(TAG, "YEAR:"+year +" month:"+month +" day:"+day);
        String str = year+"-"+month+"-"+day;
        mInterface.onDateSelected(str);
    }
}
