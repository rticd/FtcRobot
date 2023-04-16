package org.firstinspires.ftc.teamcode.Autonomous.Camera.Util;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBlue;
import org.firstinspires.ftc.teamcode.Autonomous.State;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import android.graphics.Bitmap;
//import android.graphics.Point;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class RedConeDetectionUtil {

    private RedConeDetectionUtil() {

    }
    public static Mat extractColors(final Mat src) {
        Mat hsv = new Mat();
        Imgproc.cvtColor(src, hsv, Imgproc.COLOR_BGR2HSV);

        // Define the color ranges for red, yellow, and blue
        Scalar lowerRed = new Scalar(0, 170, 100);
        Scalar upperRed = new Scalar(10, 255, 255);
        Scalar lowerYellow = new Scalar(20, 100, 100);
        Scalar upperYellow = new Scalar(30, 255, 255);
        Scalar lowerBlue = new Scalar(90, 80, 80);
        Scalar upperBlue = new Scalar(140, 200, 255);

        // Create masks for red, yellow, and blue colors
        Mat redMask = new Mat();
        Mat yellowMask = new Mat();
        Mat blueMask = new Mat();

        Core.inRange(hsv, lowerRed, upperRed, redMask);
        Core.inRange(hsv, lowerYellow, upperYellow, yellowMask);
        Core.inRange(hsv, lowerBlue, upperBlue, blueMask);

        // Combine the masks
        Mat combinedMask = new Mat();
        Core.addWeighted(redMask, 1.0, yellowMask, 1.0, 0.0, combinedMask);
        Core.addWeighted(combinedMask, 1.0, blueMask, 1.0, 0.0, combinedMask);

        // Apply the combined mask to the input image
        Mat result = new Mat();
        src.copyTo(result, combinedMask);

        return result;
    }
    public static Bitmap convertMatToBitmap(final Mat mat) {
        // Create a bitmap with the appropriate dimensions and color format
        final Bitmap bitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);

        // Write data from the Mat object to the Bitmap
        final ByteBuffer buffer = ByteBuffer.wrap(new byte[mat.channels() * mat.cols() * mat.rows()]);
        mat.get(0, 0, buffer.array());
        buffer.rewind();
        bitmap.copyPixelsFromBuffer(buffer);

        return bitmap;
    }
    public static Mat processImage(final Mat mat) {
        final Mat processed = new Mat(mat.height(), mat.width(), mat.type());
        // Blur an image using a Gaussian filter
        Imgproc.GaussianBlur(mat, processed, new Size(7, 7), 1);

        // Switch from RGB to GRAY
        Imgproc.cvtColor(processed, processed, Imgproc.COLOR_RGB2GRAY);

        // Find edges in an image using the Canny algorithm
        Imgproc.Canny(processed, processed, 200, 25);

        // Dilate an image by using a specific structuring element
        // https://en.wikipedia.org/wiki/Dilation_(morphology)
        Imgproc.dilate(processed, processed, new Mat(), new Point(-1, -1), 1);

        return processed;
    }
    private static boolean isBlue(double[] color) {
        // Assuming color in BGR format (Blue, Green, Red)
        final double blue = color[0];
        final double green = color[1];
        final double red = color[2];

        return blue > green && blue > red;
    }
    private static boolean isRed(double[] color) {
        // Assuming color in BGR format (Blue, Green, Red)
        final double blue = color[0];
        final double green = color[1];
        final double red = color[2];

        return red > green && blue < red;
    }
    private static RelativePosition getRelativePosition(Point center, Mat image){

        Point imageCenter = new Point(image.cols() / 2, image.rows() / 2);

        if (imageCenter.x+80 < center.x){
            return RelativePosition.right;
        } else if (imageCenter.x-80 > center.x){
            return RelativePosition.left;
        } else {
            return RelativePosition.center;
        }
    }

    //old variant which works
    public static void markOuterContour(final Mat processedImage,
                                        final Mat originalImage, State state) {
        // Find contours of an image
        final List<MatOfPoint> allContours = new ArrayList<>();
        Imgproc.findContours(
                processedImage,
                allContours,
                new Mat(processedImage.size(), processedImage.type()),
                Imgproc.RETR_EXTERNAL,
                Imgproc.CHAIN_APPROX_NONE
        );
        // Filter out noise and display contour
        final List<MatOfPoint> filteredContours = allContours.stream()
                .filter(contour -> {
                    final double value = Imgproc.contourArea(contour);

                    final boolean isNotNoise = value > 10000;

                    return isNotNoise;
                }).collect(Collectors.toList());
        // Check if object inside contour is blue and draw contours
        for (MatOfPoint contour : filteredContours) {

            final Rect rect = Imgproc.boundingRect(contour);
            final Point center = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
            final double[] color = originalImage.get((int) center.y, (int) center.x);
            if (color != null && (isBlue(color))) {

                Imgproc.drawContours(
                        originalImage,
                        Collections.singletonList(contour),
                        -1, // Negative value indicates that we want to draw all of contours
                        new Scalar(0,0,255),
                        4
                );

                // Draw green dot at the center of the contour
                Imgproc.circle(
                        originalImage,
                        center,
                        2,
                        new Scalar(0, 255, 0),
                        -1
                );

                // Display coordinates of the center point in green text
                Imgproc.putText(
                        originalImage,
                        "Center: (" + (int) center.x + ", " + (int) center.y + ")"+Imgproc.contourArea(contour),
                        new Point(center.x - 60, center.y - 10),
                        2,
                        0.5,
                        new Scalar(0, 255, 0),
                        1
                );
                AutoBlue.conePosition = getRelativePosition(center, originalImage);
                /*
                System.out.print("\033[H\033[2J"); // ANSI escape code to clear console
                System.out.flush();
                System.out.println(getRelativePosition(center, originalImage));
    */


            } else {
                Imgproc.drawContours(
                        originalImage,
                        Collections.singletonList(contour),
                        -1, // Negative value indicates that we want to draw all of contours
                        new Scalar(124, 252, 0), // Green color
                        4
                );
            }
        }
        displayCentre(originalImage);

    }



    /* public static void markOuterContour(final Mat processedImage,
                                         final Mat originalImage) {
         // Find contours of an image
         final List<MatOfPoint> allContours = new ArrayList<>();
         Imgproc.findContours(
                 processedImage,
                 allContours,
                 new Mat(processedImage.size(), processedImage.type()),
                 Imgproc.RETR_EXTERNAL,
                 Imgproc.CHAIN_APPROX_NONE
         );
         final List<MatOfPoint> filteredContours = allContours.stream()
                 .filter(contour -> {
                     final double value = Imgproc.contourArea(contour);

                     final boolean isNotNoise = value > 10000;

                     return isNotNoise;
                 }).collect(Collectors.toList());

         MatOfPoint largestBlueContour = null;
         double maxArea = 0;
         Point largestCenter = null;

         // Find the largest blue contour
         for (MatOfPoint contour : filteredContours) {

             final double value = Imgproc.contourArea(contour);
             final Rect rect = Imgproc.boundingRect(contour);
             final Point center = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
             final double[] color = originalImage.get((int) center.y, (int) center.x);

             if (color != null && (isBlue(color)) && value > 10000) {
                 if (value > maxArea) {
                     maxArea = value;
                     largestBlueContour = contour;
                     largestCenter = center;
                 }
             }
         }

         // Draw contours for all objects
         for (MatOfPoint contour : filteredContours) {
             final Rect rect = Imgproc.boundingRect(contour);
             final Point center = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
             //final double[] color = originalImage.get((int) center.y, (int) center.x);

             if (contour.equals(largestBlueContour)) {
                 // Draw red contour around the largest blue object
                 Imgproc.drawContours(
                         originalImage,
                         Collections.singletonList(contour),
                         -1,
                         new Scalar(0, 0, 255),
                         4
                 );
                 // Display coordinates of the largest blue object's center point in green text
                 Imgproc.putText(
                         originalImage,
                         "Center: (" + (int) center.x + ", " + (int) center.y + ")",
                         new Point(center.x - 60, center.y - 10),
                         2,
                         0.5,
                         new Scalar(0, 255, 0),
                         1
                 );

             } else {
                 // Draw green contour around other objects
                 Imgproc.drawContours(
                         originalImage,
                         Collections.singletonList(contour),
                         -1,
                         new Scalar(124, 252, 0),
                         4
                 );
             }
         }

         displayCentre(originalImage);
     }



     */
    private static void displayCentre(Mat originalImage){
        // Calculate the center of the input image
        int centerX = originalImage.cols() / 2;
        int centerY = originalImage.rows() / 2;
        Point centerOfImage = new Point(centerX, centerY);

        // Draw an orange point at the center of the input image
        Scalar orangeColor = new Scalar(0, 165, 255);
        int pointRadius = 2;
        int pointThickness = -1; // Filled circle
        Imgproc.circle(originalImage, centerOfImage, pointRadius, orangeColor, pointThickness);

        // Display coordinates of the center of the input image
        String centerCoordinatesText = "Center: (" + centerX + ", " + centerY + ")";
        Point textPosition = new Point(centerX - 60, centerY - 10);
        int fontFace = 2;
        double fontScale = 0.5;
        int textThickness = 1;
        Imgproc.putText(originalImage, centerCoordinatesText, textPosition, fontFace, fontScale, orangeColor, textThickness);

    }

}

