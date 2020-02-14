package com.sara.gnamm.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.sara.gnamm.R;
import com.sara.gnamm.extensions.UserHelper;
import com.sara.gnamm.models.meal.Recipe;
import com.sara.gnamm.repository.RecipeRepositoryMock;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecipeRepositoryMock repo = new RecipeRepositoryMock();

        AppCompatTextView name = view.findViewById(R.id.recipe_detail_name);
        AppCompatTextView author = view.findViewById(R.id.recipe_detail_author);
        AppCompatTextView description = view.findViewById(R.id.recipe_detail_description);
        AppCompatButton btn = view.findViewById(R.id.generate_btn);

        btn.setOnClickListener(v -> {

            Recipe recipe = Recipe.mock();

            name.setText(recipe.getName());
            author.setText(getString(R.string.recipe_author, UserHelper.displayName(recipe.getUser())));
            description.setText(recipe.getDescription());

            ((AppCompatTextView) view.findViewById(R.id.test_findAll)).setText("Repository find all count: " + repo.findAll().size());

            repo.save(recipe);
            ((AppCompatTextView) view.findViewById(R.id.test_findAll1)).setText("Repository find all after add count: " + repo.findAll().size());

            ((AppCompatTextView) view.findViewById(R.id.test_findbyPsw)).setText("Repository find by id: " + repo.find(recipe.getId(), recipe.getUser().getId()));

            recipe.setName("Zuppa di zucca");
            ((AppCompatTextView) view.findViewById(R.id.test_findAll4)).setText("Repository update: " + repo.update(recipe));

            repo.delete(recipe);
            ((AppCompatTextView) view.findViewById(R.id.test_findAll5)).setText("Repository delete: " + repo.findAll().size());

        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
