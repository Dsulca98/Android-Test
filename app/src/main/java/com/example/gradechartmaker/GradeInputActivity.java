package com.example.gradechartmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

//AppCompatActivity
public class GradeInputActivity extends AppCompatActivity {
    private EditText mNumStuTotal,mNumStuA,mNumStuB,mNumStuC,mNumStuD,mNumStuF;
    static String dialogMsg;
    static float[] percentArr;
    private int []gradesArr;
    private int totalStu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_input);
        //Linking the Views to Java using the their xml id
        mNumStuTotal=(EditText)findViewById(R.id.totalStudents);
        mNumStuA=(EditText)findViewById(R.id.numStudentsA);
        mNumStuB=(EditText)findViewById(R.id.numStudentsB);
        mNumStuC=(EditText)findViewById(R.id.numStudentsC);
        mNumStuD=(EditText)findViewById(R.id.numStudentsD);
        mNumStuF=(EditText)findViewById(R.id.numStudentsF);
        //computeButton=(Button)findViewById(R.id.computeButton);
    }
    //upon click of the compute button, this function will be called
    public void computeGrades(View view) {
        //if the button is clicked, first we have to check if the input is empty
        if(checkEmpty()){
            //if empty, the code will show a toast and will force the user to enter an input
            Toast.makeText(getApplicationContext(),"Invalid Entry",Toast.LENGTH_SHORT).show();
        }else{
            //if proper input is entered, the rates will be calculated
            calcRates();
            //after the rates are calculated,the dialog will show the rates, in this case I use a static String to
            //pass the message to the ComputedDialog class
            setDialogMessage(percentArr);
            //dialog is called to be initialized
            openDialog();
            }
    }
    public  void openDialog(){
        //I created a class to call a dialog,I instantiated in this function
        ComputedDialog dialog= new ComputedDialog();
        dialog.show(getSupportFragmentManager(),"message");
    }
    public void calcRates(){
        int numGrades = gradesArr.length;//must return 5, the number of elements in gradesArr
        //created a float array to hold the percentages
        percentArr=new float[numGrades];
        //this for loop will initialize the percentArr with the correct percentages
        for(int i=0;i<5;i++){
            percentArr[i]=((float) gradesArr[i]/totalStu)*100;
        }
        //gradesArr=new int[]{};
        //int numTotalStu,perStuA,perStuB,perStuD,perStuE,perStuF;
    }

    public boolean checkEmpty(){
        //The Strings are created and initialized to check if they have any value
        String A,B,C,D,F;
        A=mNumStuB.getText().toString();
        B=mNumStuB.getText().toString();
        C=mNumStuC.getText().toString();
        D=mNumStuD.getText().toString();
        F=mNumStuF.getText().toString();
        //if any EditText is not initialized, it will prevent from going further
        if((mNumStuTotal.getText().toString()).equals("")||A.equals("")||B.equals("")||C.equals("")||D.equals("")||F.equals("")){
            return true;
        }
        //the numbers are set now that everything is initialized
        setNums();
        //this array hold temporary array of numbers to check if the total is the same
        // as the total number of students entered, else it will prevent from going forward
        int []checkArr=getGradesArr();
        if((checkArr[0]+checkArr[1]+checkArr[2]+checkArr[3]+checkArr[4])!=totalStu){
        return true;}
        //if everything is correct, this will return false and continue the code
        else{
            return false;
        }
    }
    public int[] getGradesArr(){
        //returns the array containing the grades
        return gradesArr;
    }
    public void setNums() {
        //sets all the EditText into ints
        totalStu=Integer.parseInt(mNumStuTotal.getText().toString());
        gradesArr= new int[]{Integer.parseInt(mNumStuA.getText().toString()),
                        Integer.parseInt(mNumStuB.getText().toString()),
                        Integer.parseInt(mNumStuC.getText().toString()),
                        Integer.parseInt(mNumStuD.getText().toString()),
                        Integer.parseInt(mNumStuF.getText().toString())};
    }
    //this sets the dialog message that is passed into the ComputedDialog class
    public void setDialogMessage(float[]percentArr){
        DecimalFormat decimalFormat=new DecimalFormat(" 0.00");
       dialogMsg="As: "+decimalFormat.format(percentArr[0])+
               "%\nBs: "+decimalFormat.format(percentArr[1])+"%\nCs: "+
               decimalFormat.format(percentArr[2])+"%\nDs: "+
               decimalFormat.format(percentArr[3])+"%\nFs: "+
               decimalFormat.format(percentArr[4])+"%";
    }
}