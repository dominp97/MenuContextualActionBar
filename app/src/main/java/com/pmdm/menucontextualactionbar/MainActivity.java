package com.pmdm.menucontextualactionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creamos lista de lannisters (seleccionable múltiple) para el
        //Action Mode Context Menu
        ListView listaLannisters = (ListView) findViewById(R.id.listaLannisters);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,
                getResources().getStringArray(R.array.lannisters));

        listaLannisters.setAdapter(adaptador);
        listaLannisters.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> p, View v, int position, long id) {

        // Start the CAB using the ActionMode.Callback defined above
        mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
        v.setSelected(true);

    }

    //Menú ActionMode
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.lannisters, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Se llama a este método cuando se ha pulsado en la lista de los lannisters
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.aniquilar:
                    //hay que crear un Aniquilar() para recorrer todos los elementos seleccionado (checked) en la listView
                    Toast.makeText(getApplicationContext(), "Hemos aniquilado a algún Lannister",Toast.LENGTH_LONG).show();
                    return true;
                case R.id.encerrar:
                    Toast.makeText(getApplicationContext(), "Hemos encerrado a algún Lannister", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.salvar:
                    Toast.makeText(getApplicationContext(), "Hemos salvado a algún Lannister", Toast.LENGTH_LONG).show();
                    return true;

                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

}