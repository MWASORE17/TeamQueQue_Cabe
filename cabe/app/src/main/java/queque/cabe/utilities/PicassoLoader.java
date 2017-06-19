package queque.cabe.utilities;

import android.content.Context;
import android.os.Environment;
import android.widget.ImageView;


import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.io.IOException;

import queque.cabe.R;

/**
 * Created by Willy Laurents on 09/07/2016.
 */
public class PicassoLoader {

    public static void loadImageSuccess(Context context, String url, ImageView image){
        Picasso.with(context).load(url).error(R.mipmap.img_nophoto).noFade().into(image);
    }

    public static void loadImageFail(Context context, ImageView image){
        Picasso.with(context).load(R.mipmap.img_nophoto).error(R.mipmap.img_nophoto).into(image);
    }
}

