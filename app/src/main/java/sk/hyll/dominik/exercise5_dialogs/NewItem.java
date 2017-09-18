package sk.hyll.dominik.exercise5_dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NewItem extends DialogFragment {

    public interface NewItemListener {
        public void onNewItemCreated(String newItem);
    }

    private NewItemListener mListener;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (NewItemListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + getString(R.string.listener_implement_error));
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_new_item, null);
        builder.setView(dialogView);
        final EditText et = dialogView.findViewById(R.id.item_name);


        builder.setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name = et.getText().toString();
                        if (name == null || name.equals("")) return;
                        mListener.onNewItemCreated(name);
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), R.string.cancel, Toast.LENGTH_SHORT).show();
                    }
                });


        return builder.create();
    }
}
