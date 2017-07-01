package pe.ironbit.android.baking.data.base;

import org.json.JSONException;

import java.util.List;

/**
 * Interface used to populate model objects with information retrieved from the internet.
 */
public interface DataParser {
    List parse(String input) throws JSONException;
}
