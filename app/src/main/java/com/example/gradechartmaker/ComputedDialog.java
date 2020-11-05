package com.example.gradechartmaker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


public class ComputedDialog extends AppCompatDialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Alert dialog part of appcompat is used to BUILD the dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //We pass the Tile, and the message coming from GradeInputActivity as a static String
        builder.setTitle("Grade Distributions %:")
                .setMessage(GradeInputActivity.dialogMsg)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    //OnClick will quit the dialog
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //new Intent will call the BarGraph
                Intent barGraph=new Intent(getActivity(),BarGraph.class);
                startActivity(barGraph);

            }
        }
        );

        return builder.create();

    }

}
