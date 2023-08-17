package com.mygdx.game.ui.overlayShapes;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.View;

import java.util.ArrayList;

public class ImageColumnView extends View {

    public ArrayList<ImageView> imagesList;
    int imagesPadding;

    public ImageColumnView(float x, float y, int height, int imagesPadding) {
        super(x, y);
        this.imagesPadding = imagesPadding;
        this.height = height;

        imagesList = new ArrayList<>();
    }

    public void addImage(String imageSrc) {
        ImageView imageView = new ImageView(0, 0, 0, imageSrc);
        imagesList.add(imageView);
        recalculateImages();
    }

    private void recalculateImages() {
        int newHeight = (int) ((height - (imagesList.size() - 1) * imagesPadding) / imagesList.size());

        for (int i = 0; i < imagesList.size(); i++) {
            ImageView imageView = imagesList.get(i);
            imageView.height = newHeight;
            imageView.width = (float) imageView.imgTexture.getWidth() / imageView.imgTexture.getHeight() * imageView.height;
            imageView.x = x;
            imageView.y = y + newHeight * i + imagesPadding * i;
        }
    }

    @Override
    public void draw(MyGdxGame myGdxGame) {
        for (ImageView imageView : imagesList) {
            imageView.draw(myGdxGame);
        }
    }
    public String getImgSource(float tx, float ty) {
        for (ImageView view: imagesList) {
            if (view.isHit(tx, ty)) {
                return view.getImgSource();
            }
        }
        return "";
    }
}
