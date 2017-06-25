package com.example.admin.starwingsapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;


public class SelectDialog extends DialogFragment {
	CharSequence[] items = { "App", "AVA", "LMS", "Registration", "Others" };
	String selection;

	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Select");
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				selection = (String) items[arg1];

			}
		});
		return builder.create();
	}

}
