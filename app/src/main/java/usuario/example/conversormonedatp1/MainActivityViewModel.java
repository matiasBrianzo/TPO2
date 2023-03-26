package usuario.example.conversormonedatp1;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData<Double> conversion;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }

    public LiveData<Double> getConversion(){

        if(conversion==null){
            this.conversion= new MutableLiveData<Double>();
        }
        return conversion;
    }


    public void ConvertirA(String pDolar, String pEuro, boolean chDolar, boolean chEuro) {

        try {
            //Dolar a Euro
            if (chDolar)
            {
                double nro1=  Double.parseDouble(pDolar);
                double nro2=  nro1*0.93;
                conversion.setValue(nro2);
            }
            //Euro a Dolar
            if (chEuro)
            {
                double nro1=  Double.parseDouble(pEuro);
                double nro2=  nro1*1.08;
                conversion.setValue(nro2);
            }

        }
        catch (NumberFormatException ex)
        {
            Toast.makeText(context, "Ingrese un numero", Toast.LENGTH_LONG).show();
        }

    }


    }


