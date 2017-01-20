package TetrisVDM;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PivotPiece extends Piece {
  protected Number currentRotation;

  public void cg_init_PivotPiece_1(
      final Number x, final Number y, final Object t, final Character s) {

    currentRotation = 1L;
    cg_init_Piece_1(x, y, ((Object) t), s);
  }

  public PivotPiece(final Number x, final Number y, final Object t, final Character s) {

    cg_init_PivotPiece_1(x, y, t, s);
  }

  public Number getRotation() {

    return currentRotation;
  }

  public void setRotation(final Number rot) {

    currentRotation = rot;
    sprite = rotateSprite(Utils.copy(originalTiles));
  }

  public void rotate() {

    rotation();
  }

  protected void rotation() {

    if (Utils.equals(currentRotation, 4L)) {
      currentRotation = 1L;
    } else {
      currentRotation = currentRotation.longValue() + 1L;
    }

    sprite = rotateSprite(Utils.copy(originalTiles));
    sprite = translateSprite(Utils.copy(sprite), translation.x, translation.y);
  }

  protected VDMSeq rotateSprite(final VDMSeq sp) {

    VDMSeq newSp = Utils.copy(sp);
    long toVar_2 = sp.size();

    for (Long inc = 1L; inc <= toVar_2; inc++) {
      ((Piece.Coordinate) Utils.get(newSp, inc)).x =
          ((Piece.Coordinate) Utils.get(sp, inc)).x.longValue() * cos(currentRotation).longValue()
              + ((Piece.Coordinate) Utils.get(sp, inc)).y.longValue()
                  * sin(currentRotation).longValue();
      ((Piece.Coordinate) Utils.get(newSp, inc)).y =
          ((Piece.Coordinate) Utils.get(sp, inc)).y.longValue() * cos(currentRotation).longValue()
              - ((Piece.Coordinate) Utils.get(sp, inc)).x.longValue()
                  * sin(currentRotation).longValue();
    }
    return Utils.copy(newSp);
  }

  private static Number cos(final Number rot) {

    Number cos = 0L;
    Number intPattern_4 = rot;
    Boolean success_3 = Utils.equals(intPattern_4, 1L);

    if (!(success_3)) {
      Number intPattern_5 = rot;
      success_3 = Utils.equals(intPattern_5, 2L);

      if (!(success_3)) {
        Number intPattern_6 = rot;
        success_3 = Utils.equals(intPattern_6, 3L);

        if (!(success_3)) {
          Number intPattern_7 = rot;
          success_3 = Utils.equals(intPattern_7, 4L);

          if (success_3) {
            cos = 0L;
          }

        } else {
          cos = -1L;
        }

      } else {
        cos = 0L;
      }

    } else {
      cos = 1L;
    }

    return cos;
  }

  private static Number sin(final Number rot) {

    Number cos = 0L;
    Number intPattern_8 = rot;
    Boolean success_4 = Utils.equals(intPattern_8, 1L);

    if (!(success_4)) {
      Number intPattern_9 = rot;
      success_4 = Utils.equals(intPattern_9, 2L);

      if (!(success_4)) {
        Number intPattern_10 = rot;
        success_4 = Utils.equals(intPattern_10, 3L);

        if (!(success_4)) {
          Number intPattern_11 = rot;
          success_4 = Utils.equals(intPattern_11, 4L);

          if (success_4) {
            cos = -1L;
          }

        } else {
          cos = 0L;
        }

      } else {
        cos = 1L;
      }

    } else {
      cos = 0L;
    }

    return cos;
  }

  public PivotPiece() {}

  public String toString() {

    return "PivotPiece{" + "currentRotation := " + Utils.toString(currentRotation) + "}";
  }
}
