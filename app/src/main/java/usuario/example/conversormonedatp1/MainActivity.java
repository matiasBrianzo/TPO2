package usuario.example.conversormonedatp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import usuario.example.conversormonedatp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Creo ViewModel
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);


        binding.rbDolares.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.etDolar.setEnabled(isChecked);
                binding.rbEuro.setChecked(false);
                binding.etEuros.setText(0+"");
                binding.etResultado.setText(0 + "");
            }
        });

        binding.rbEuro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.etEuros.setEnabled(isChecked);
                binding.rbDolares.setChecked(false);
                binding.etDolar.setText(0+"");
                binding.etResultado.setText(0 + "");
            }
        });
        //Coloco el observer al mutable del ViewModel

        mv.getConversion().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double value) {
                binding.etResultado.setText(value + "");
            }
        });

        binding.btCombertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.ConvertirA(binding.etDolar.getText().toString(), binding.etEuros.getText().toString(), binding.rbDolares.isChecked(), binding.rbEuro.isChecked());
            }
        });

    }
}