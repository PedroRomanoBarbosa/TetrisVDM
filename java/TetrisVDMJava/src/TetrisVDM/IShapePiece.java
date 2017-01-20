package TetrisVDM;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class IShapePiece extends PivotPiece {
  public void cg_init_IShapePiece_1(final Number x, final Number y, final Character s) {

    cg_init_PivotPiece_1(x, y, TetrisVDM.quotes.IQuote.getInstance(), s);
  }

  public IShapePiece(final Number x, final Number y, final Character s) {

    cg_init_IShapePiece_1(x, y, s);
  }

  public void rotate() {

    rotation();
    Number intPattern_1 = currentRotation;
    Boolean success_1 = Utils.equals(intPattern_1, 2L);

    if (!(success_1)) {
      Number intPattern_2 = currentRotation;
      success_1 = Utils.equals(intPattern_2, 3L);

      if (!(success_1)) {
        Number intPattern_3 = currentRotation;
        success_1 = Utils.equals(intPattern_3, 4L);

        if (success_1) {
          sprite = translateSprite(Utils.copy(sprite), 0L, -1L);
        }

      } else {
        sprite = translateSprite(Utils.copy(sprite), 1L, -1L);
      }

    } else {
      sprite = translateSprite(Utils.copy(sprite), 1L, 1L);
    }
  }

  public IShapePiece() {}

  public String toString() {

    return "IShapePiece{}";
  }
}
