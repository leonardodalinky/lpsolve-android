package lp.lp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import lpsolve.LpSolve;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            LpSolve problem = LpSolve.makeLp(0, 5);
            problem.setMinim();
            problem.setObjFn(new double[]{0.0, -2.0, -1.0, -4.0, -3.0, -1.0});
            problem.addConstraint(new double[]{0, 0, 2.0, 1.0, 4.0, 2.0}, LpSolve.LE, 54);
            problem.addConstraint(new double[]{0, 3, 4, 5, -1, -1}, LpSolve.LE, 62);
            problem.setLowbo(3, 3.32);
            problem.setLowbo(4, 0.678);
            problem.setLowbo(5, 2.57);
            problem.setVerbose(LpSolve.IMPORTANT);
            problem.printLp();
            int ret = problem.solve();//具体返回值信息请参考lpsolve文档
            Log.e(TAG, String.valueOf(ret));
            Log.e(TAG, String.valueOf(problem.getSolutioncount()));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
