package rrozanski.wisiszmihajs;

import android.databinding.BindingAdapter;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by robert on 29.08.2017.
 */

public class DataBindingAdapters {

    @BindingAdapter("layout_weight_binding_horizontal")
    public static void setLayoutWeight(View view, int weight) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.weight = weight;
        params.width = 0;
        view.setLayoutParams(params);
    }

    @BindingAdapter("editable_edit_text")
    public static void setEditTextEditable(EditText editText, boolean editMode) {
        if (editMode) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
    }
}
