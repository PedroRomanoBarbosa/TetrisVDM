package TetrisVDM;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TetrisTest {
  public static void main() {

    TetrisTest tetrisTest = new TetrisTest();
    tetrisTest.testGame();
    tetrisTest.testRandomCommands();
    tetrisTest.testPiece();
    tetrisTest.testPivotPiece();
    tetrisTest.testIShapePiece();
  }

  private void assertTrue(final Boolean cond) {

    return;
  }

  public void testGame() {

    Tetris tetris = null;
    long toVar_12 = 10L;

    for (Long iter = 1L; iter <= toVar_12; iter++) {
      tetris = new Tetris(22L, 10L);
      tetris.init();
      Boolean whileCond_6 = true;
      while (whileCond_6) {
        whileCond_6 = !(tetris.isGameFinished());
        if (!(whileCond_6)) {
          break;
        }

        {
          Piece.Coordinate lastTranslation = null;
          Number lastRotation = 1L;
          Number nextRotation = 1L;
          lastTranslation = tetris.getCurrentPiece().getTranslation();
          tetris.moveCurrentPieceDown();
          Boolean orResult_8 = false;

          if (Utils.equals(
              tetris.getCurrentPiece().getTranslation().y, lastTranslation.y.longValue() + 1L)) {
            orResult_8 = true;
          } else {
            orResult_8 =
                Utils.equals(tetris.getCurrentPiece().getTranslation().y, lastTranslation.y);
          }

          assertTrue(orResult_8);

          lastTranslation = tetris.getCurrentPiece().getTranslation();
          tetris.moveCurrentPieceLeft();
          Boolean orResult_9 = false;

          if (Utils.equals(
              tetris.getCurrentPiece().getTranslation().x, lastTranslation.x.longValue() - 1L)) {
            orResult_9 = true;
          } else {
            orResult_9 =
                Utils.equals(tetris.getCurrentPiece().getTranslation().x, lastTranslation.x);
          }

          assertTrue(orResult_9);

          lastTranslation = tetris.getCurrentPiece().getTranslation();
          tetris.moveCurrentPieceRight();
          Boolean orResult_10 = false;

          if (Utils.equals(
              tetris.getCurrentPiece().getTranslation().x, lastTranslation.x.longValue() + 1L)) {
            orResult_10 = true;
          } else {
            orResult_10 =
                Utils.equals(tetris.getCurrentPiece().getTranslation().x, lastTranslation.x);
          }

          assertTrue(orResult_10);

          long toVar_11 = 5L;

          for (Long inc = 1L; inc <= toVar_11; inc++) {
            lastRotation = tetris.getCurrentPiece().getRotation();
            tetris.rotate();
            if (Utils.equals(lastRotation, 4L)) {
              nextRotation = 1L;
            } else {
              nextRotation = lastRotation.longValue() + 1L;
            }

            Boolean orResult_11 = false;

            if (Utils.equals(nextRotation, tetris.getCurrentPiece().getRotation())) {
              orResult_11 = true;
            } else {
              orResult_11 = Utils.equals(tetris.getCurrentPiece().getRotation(), lastRotation);
            }

            assertTrue(orResult_11);
          }
          tetris.drop();
          assertTrue(tetris.checkDownCollision(tetris.getCurrentPiece().getSprite()));
          tetris.next();
        }
      }
    }
  }

  public void testRandomCommands() {

    Tetris tetris = new Tetris(22L, 10L);
    long toVar_13 = 10L;

    for (Long inc = 1L; inc <= toVar_13; inc++) {
      tetris = new Tetris(22L, 10L);
      tetris.init();
      Boolean whileCond_7 = true;
      while (whileCond_7) {
        whileCond_7 = !(tetris.isGameFinished());
        if (!(whileCond_7)) {
          break;
        }

        {
          randomCommand(tetris);
        }
      }
    }
    return;
  }

  private void randomCommand(final Tetris tetris) {

    Number rand = MATH.rand(5L).longValue() + 1L;
    Number intPattern_12 = rand;
    Boolean success_5 = Utils.equals(intPattern_12, 1L);

    if (!(success_5)) {
      Number intPattern_13 = rand;
      success_5 = Utils.equals(intPattern_13, 2L);

      if (!(success_5)) {
        Number intPattern_14 = rand;
        success_5 = Utils.equals(intPattern_14, 3L);

        if (!(success_5)) {
          Number intPattern_15 = rand;
          success_5 = Utils.equals(intPattern_15, 4L);

          if (!(success_5)) {
            Number intPattern_16 = rand;
            success_5 = Utils.equals(intPattern_16, 5L);

            if (success_5) {
              {
                tetris.drop();
                tetris.next();
              }
            }

          } else {
            tetris.rotate();
          }

        } else {
          tetris.moveCurrentPieceRight();
        }

      } else {
        tetris.moveCurrentPieceLeft();
      }

    } else {
      tetris.moveCurrentPieceDown();
    }
  }

  public void testPiece() {

    Piece p = new Piece(10L, 5L, TetrisVDM.quotes.OQuote.getInstance(), 'O');
    Piece p2 = null;
    VDMSeq pieceTypes =
        SeqUtil.seq(
            TetrisVDM.quotes.OQuote.getInstance(),
            TetrisVDM.quotes.TQuote.getInstance(),
            TetrisVDM.quotes.IQuote.getInstance(),
            TetrisVDM.quotes.LQuote.getInstance(),
            TetrisVDM.quotes.JQuote.getInstance(),
            TetrisVDM.quotes.ZQuote.getInstance(),
            TetrisVDM.quotes.SQuote.getInstance());
    Piece.Coordinate lastTranslation = null;
    Boolean andResult_41 = false;

    if (Utils.equals(p.getTranslation().x, 10L)) {
      Boolean andResult_42 = false;

      if (Utils.equals(p.getTranslation().y, 5L)) {
        Boolean andResult_43 = false;

        if (Utils.equals(p.getSymbol(), 'O')) {
          Boolean andResult_44 = false;

          if (Utils.equals(
              p.getOriginalSprite(),
              SeqUtil.seq(
                  new Piece.Coordinate(0L, 0L),
                  new Piece.Coordinate(1L, 1L),
                  new Piece.Coordinate(1L, 0L),
                  new Piece.Coordinate(0L, 1L)))) {
            if (Utils.equals(
                p.getSprite(),
                SeqUtil.seq(
                    new Piece.Coordinate(0L + 10L, 0L + 5L),
                    new Piece.Coordinate(1L + 10L, 1L + 5L),
                    new Piece.Coordinate(1L + 10L, 0L + 5L),
                    new Piece.Coordinate(0L + 10L, 1L + 5L)))) {
              andResult_44 = true;
            }
          }

          if (andResult_44) {
            andResult_43 = true;
          }
        }

        if (andResult_43) {
          andResult_42 = true;
        }
      }

      if (andResult_42) {
        andResult_41 = true;
      }
    }

    assertTrue(andResult_41);

    p.setTranslation(1L, 1L);
    Boolean andResult_45 = false;

    if (Utils.equals(p.getTranslation().x, 1L)) {
      if (Utils.equals(p.getTranslation().y, 1L)) {
        andResult_45 = true;
      }
    }

    assertTrue(andResult_45);

    p.setTranslation(-1L, 1L);
    Boolean andResult_46 = false;

    if (Utils.equals(p.getTranslation().x, -1L)) {
      if (Utils.equals(p.getTranslation().y, 1L)) {
        andResult_46 = true;
      }
    }

    assertTrue(andResult_46);

    p.setTranslation(1L, -1L);
    Boolean andResult_47 = false;

    if (Utils.equals(p.getTranslation().x, 1L)) {
      if (Utils.equals(p.getTranslation().y, -1L)) {
        andResult_47 = true;
      }
    }

    assertTrue(andResult_47);

    p.setTranslation(-1L, -1L);
    Boolean andResult_48 = false;

    if (Utils.equals(p.getTranslation().x, -1L)) {
      if (Utils.equals(p.getTranslation().y, -1L)) {
        andResult_48 = true;
      }
    }

    assertTrue(andResult_48);

    lastTranslation = p.getTranslation();
    p.move(2L, 2L);
    Boolean andResult_49 = false;

    if (Utils.equals(p.getTranslation().x, lastTranslation.x.longValue() + 2L)) {
      if (Utils.equals(p.getTranslation().y, lastTranslation.y.longValue() + 2L)) {
        andResult_49 = true;
      }
    }

    assertTrue(andResult_49);

    p.rotate();
    assertTrue(Utils.equals(p.getRotation(), 1L));
    p.setRotation(2L);
    assertTrue(Utils.equals(p.getRotation(), 1L));
    long toVar_14 = pieceTypes.size();

    for (Long inc = 1L; inc <= toVar_14; inc++) {
      p2 = Piece.createNewPiece(((Object) Utils.get(pieceTypes, inc)), 'X');
      assertTrue(
          Utils.equals(
              p2.getOriginalSprite(),
              ((VDMSeq) Utils.get(Piece.DEFAULT_SPRITES, Utils.get(pieceTypes, inc)))));
    }
  }

  public void testPivotPiece() {

    PivotPiece p = new PivotPiece(10L, 5L, TetrisVDM.quotes.LQuote.getInstance(), 'L');
    Number lastRotation = 1L;
    Number nextRotation = 1L;
    long toVar_15 = 5L;

    for (Long inc = 1L; inc <= toVar_15; inc++) {
      lastRotation = p.getRotation();
      if (Utils.equals(lastRotation, 4L)) {
        nextRotation = 1L;
      } else {
        nextRotation = lastRotation.longValue() + 1L;
      }

      p.rotate();
      assertTrue(Utils.equals(p.getRotation(), nextRotation));
    }
    p = new PivotPiece(0L, 0L, TetrisVDM.quotes.LQuote.getInstance(), 'L');
    p.setRotation(1L);
    assertTrue(Utils.equals(p.getRotation(), 1L));
    assertTrue(Utils.equals(p.getSprite(), p.getOriginalSprite()));
    p.setRotation(2L);
    assertTrue(Utils.equals(p.getRotation(), 2L));
    assertTrue(
        Utils.equals(
            p.getSprite(),
            SeqUtil.seq(
                new Piece.Coordinate(1L, 0L),
                new Piece.Coordinate(-1L, 0L),
                new Piece.Coordinate(0L, 0L),
                new Piece.Coordinate(1L, -1L))));
    p.setRotation(3L);
    assertTrue(Utils.equals(p.getRotation(), 3L));
    assertTrue(
        Utils.equals(
            p.getSprite(),
            SeqUtil.seq(
                new Piece.Coordinate(0L, -1L),
                new Piece.Coordinate(0L, 1L),
                new Piece.Coordinate(0L, 0L),
                new Piece.Coordinate(-1L, -1L))));
    p.setRotation(4L);
    assertTrue(Utils.equals(p.getRotation(), 4L));
    assertTrue(
        Utils.equals(
            p.getSprite(),
            SeqUtil.seq(
                new Piece.Coordinate(-1L, 0L),
                new Piece.Coordinate(1L, 0L),
                new Piece.Coordinate(0L, 0L),
                new Piece.Coordinate(-1L, 1L))));
  }

  public void testIShapePiece() {

    IShapePiece p = new IShapePiece(0L, 0L, 'I');
    p.rotate();
    assertTrue(
        Utils.equals(
            p.getSprite(),
            SeqUtil.seq(
                new Piece.Coordinate(0L, 2L),
                new Piece.Coordinate(0L, 1L),
                new Piece.Coordinate(0L, 0L),
                new Piece.Coordinate(0L, -1L))));
    p.rotate();
    assertTrue(
        Utils.equals(
            p.getSprite(),
            SeqUtil.seq(
                new Piece.Coordinate(2L, 0L),
                new Piece.Coordinate(1L, 0L),
                new Piece.Coordinate(0L, 0L),
                new Piece.Coordinate(-1L, 0L))));
    p.rotate();
    assertTrue(
        Utils.equals(
            p.getSprite(),
            SeqUtil.seq(
                new Piece.Coordinate(1L, -2L),
                new Piece.Coordinate(1L, -1L),
                new Piece.Coordinate(1L, 0L),
                new Piece.Coordinate(1L, 1L))));
    p.rotate();
    assertTrue(
        Utils.equals(
            p.getSprite(),
            SeqUtil.seq(
                new Piece.Coordinate(-1L, -1L),
                new Piece.Coordinate(0L, -1L),
                new Piece.Coordinate(1L, -1L),
                new Piece.Coordinate(2L, -1L))));
  }

  public TetrisTest() {}

  public String toString() {

    return "TetrisTest{}";
  }
}
