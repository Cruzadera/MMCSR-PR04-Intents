package es.iessaladillo.pedrojoya.pr04.utils;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;

public class IntentsImplicitUtils {
    private IntentsImplicitUtils(){}

    // MEJOR PASA UNA CADENA EN VEZ DE UN EditText. PODRÁS REUTILIZAR EL MÉTODO
    // EN OTRAS CIRCUNSTANCIAS. (LO MISMO PARA EL RESTO DE MÉTODOS DE ESTA CLASE).
    public static Intent sendEmail(EditText txt){
        String email = txt.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        // FALLA EL TEST PORQUE AL USAR Uri.fromParts() SE DEVUELVE UNA Uri Y NO
        // SE USA DIRECTAMENTE UNA CADENA COMO EN EL TEST. TU VERSIÓN ES MEJOR QUE LA MÍA.
        intent.setData(Uri.fromParts("mailto", email, null));
        return intent;
    }

    public static Intent openMaps(EditText txt){
        String address = txt.getText().toString();
        // AL USAR Uri.encode FALLA EL TEST. FALLO MÍO EN EL TEST, QUE DEBERÍA
        // INCORPORAR EL Uri.encode()
        Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        return mapIntent;
    }

    public static Intent startCall(EditText txt){
        String phoneNumber = txt.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    public static Intent searchWeb(EditText txt){
        String web = txt.getText().toString();
        // LA ACCIÓN DE TEST ERA ACTION_VIEW. POR ESO FALLA EL TEST CORRESPONDIENTE.
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, web); // query contains search string
        return intent;
    }
}
