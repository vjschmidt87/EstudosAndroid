package example.fragment.vinicius.com.br.fragmentsbasics;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by vinicius.schmidt on 27/03/2017.
 */

public class ArticleFragment extends Fragment {
    static final String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.article_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if(args != null) {
            updateArticleView(args.getInt(ARG_POSITION), R.id.article);
        } else if(mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition, R.id.article);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    public void updateArticleView(int position, int id) {
        TextView article = (TextView) getActivity().findViewById(id);
        article.setText(MockNews.Articles[position]);
        mCurrentPosition = position;
    }
}
