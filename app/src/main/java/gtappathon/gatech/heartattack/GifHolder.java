package gtappathon.gatech.heartattack;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GifHolder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GifHolder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GifHolder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Map<String, String> urlMap;

    public GifHolder() {
        // Required empty public constructor
        urlMap = new HashMap<>();
        urlMap.put("1", "http://d36rv60zdkz1hi.cloudfront.net/supper/uploads/2014/08/url.gif");
        urlMap.put("2", "https://media.giphy.com/media/HWAOHq3SObKTK/giphy.gif");
        urlMap.put("3", "http://media1.giphy.com/media/K5CULzwBU03HG/giphy.gif");
        urlMap.put("4", "http://media.tumblr.com/tumblr_ly04o4NxyG1qlvj4w.gif");
        urlMap.put("5", "http://media.tumblr.com/tumblr_lfl0nuPiYO1qdzjnp.gif");
        urlMap.put("6", "http://images.firstwefeast.com/complex/image/upload/qir71keq7xkmlwye7sma.gif");
        urlMap.put("7", "https://thoughtcatalog.files.wordpress.com/2013/07/4.gif?w=351&h=262");urlMap.put("1", "http://d36rv60zdkz1hi.cloudfront.net/supper/uploads/2014/08/url.gif");
        urlMap.put("8", "https://spakwillsnack.files.wordpress.com/2016/04/102936-kung-fu-panda-eating-gif-imgur-4spm.gif?w=450");
        urlMap.put("9", "https://m.popkey.co/88f3e8/OV6aa.gif");
        urlMap.put("10", "https://thoughtcatalog.files.wordpress.com/2013/07/5.gif?w=430&h=288");
        urlMap.put("11", "http://stream1.gifsoup.com/view4/2034469/pie-eating-contest-o.gif");
        urlMap.put("12", "https://media.tenor.co/images/4585114d8e85229117ca3d5b4f4da7a9/raw");
        urlMap.put("13", "https://andelino.files.wordpress.com/2015/02/good-news-bad-news-04.gif");
        urlMap.put("HEART ATTACK! FINALLY!", "https://uproxx.files.wordpress.com/2014/04/joff-01.gif?w=650");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GifHolder.
     */
    // TODO: Rename and change types and number of parameters
    public static GifHolder newInstance(String param1, String param2) {
        GifHolder fragment = new GifHolder();
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
        View view = inflater.inflate(R.layout.fragment_gif_holder, container, false);
        TextView currentStatus = (TextView)view.findViewById(R.id.current_status);
        currentStatus.setText("You are at " + HeartAttackStatus.getInstance().points + " clots"
                + "\nYou are at level " + HeartAttackStatus.getInstance().getLevel());
        WebView mWebView2 =  (WebView)(view.findViewById(R.id.gif));
        mWebView2.getSettings().setJavaScriptEnabled(true);
        mWebView2.getSettings().setLoadWithOverviewMode(true);
        mWebView2.getSettings().setUseWideViewPort(true);
        mWebView2.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView2.setScrollbarFadingEnabled(true);
        String status = HeartAttackStatus.getInstance().getLevel();
        mWebView2.loadDataWithBaseURL(urlMap.get(status), "<img src=\"banner5.png\" height=\"98%\" width=\"100%\"/>", "text/html", "utf-8", null);
        mWebView2.loadUrl(urlMap.get(status));
        return view;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
