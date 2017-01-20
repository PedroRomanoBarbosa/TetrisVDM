package TetrisVDM;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Piece {
  public static final VDMMap DEFAULT_SPRITES =
      MapUtil.map(
          new Maplet(
              TetrisVDM.quotes.IQuote.getInstance(),
              SeqUtil.seq(
                  new Coordinate(-1L, -1L),
                  new Coordinate(0L, -1L),
                  new Coordinate(1L, -1L),
                  new Coordinate(2L, -1L))),
          new Maplet(
              TetrisVDM.quotes.JQuote.getInstance(),
              SeqUtil.seq(
                  new Coordinate(0L, 1L),
                  new Coordinate(0L, -1L),
                  new Coordinate(0L, 0L),
                  new Coordinate(-1L, 1L))),
          new Maplet(
              TetrisVDM.quotes.LQuote.getInstance(),
              SeqUtil.seq(
                  new Coordinate(0L, 1L),
                  new Coordinate(0L, -1L),
                  new Coordinate(0L, 0L),
                  new Coordinate(1L, 1L))),
          new Maplet(
              TetrisVDM.quotes.SQuote.getInstance(),
              SeqUtil.seq(
                  new Coordinate(1L, -1L),
                  new Coordinate(0L, -1L),
                  new Coordinate(0L, 0L),
                  new Coordinate(-1L, 0L))),
          new Maplet(
              TetrisVDM.quotes.ZQuote.getInstance(),
              SeqUtil.seq(
                  new Coordinate(-1L, -1L),
                  new Coordinate(0L, -1L),
                  new Coordinate(0L, 0L),
                  new Coordinate(1L, 0L))),
          new Maplet(
              TetrisVDM.quotes.TQuote.getInstance(),
              SeqUtil.seq(
                  new Coordinate(0L, 0L),
                  new Coordinate(0L, -1L),
                  new Coordinate(1L, 0L),
                  new Coordinate(-1L, 0L))),
          new Maplet(
              TetrisVDM.quotes.OQuote.getInstance(),
              SeqUtil.seq(
                  new Coordinate(0L, 0L),
                  new Coordinate(1L, 1L),
                  new Coordinate(1L, 0L),
                  new Coordinate(0L, 1L))));
  protected Coordinate translation;
  protected VDMSeq originalTiles;
  protected VDMSeq sprite;
  protected Character symbol;

  public void cg_init_Piece_1(final Number x, final Number y, final Object t, final Character s) {

    symbol = s;
    originalTiles = Utils.copy(((VDMSeq) Utils.get(DEFAULT_SPRITES, t)));
    sprite = Utils.copy(((VDMSeq) Utils.get(DEFAULT_SPRITES, t)));
    translation = new Coordinate(0L, 0L);
    move(x, y);
  }

  public Piece(final Number x, final Number y, final Object t, final Character s) {

    cg_init_Piece_1(x, y, t, s);
  }

  public static Piece createNewPiece(final Object type, final Character s) {

    Piece p = null;
    Object quotePattern_1 = type;
    Boolean success_2 = Utils.equals(quotePattern_1, TetrisVDM.quotes.OQuote.getInstance());

    if (!(success_2)) {
      Object quotePattern_2 = type;
      success_2 = Utils.equals(quotePattern_2, TetrisVDM.quotes.IQuote.getInstance());

      if (success_2) {
        p = new IShapePiece(0L, 0L, s);
      } else {
        p = new PivotPiece(0L, 0L, ((Object) type), s);
      }

    } else {
      p = new Piece(0L, 0L, ((Object) type), s);
    }

    return p;
  }

  public VDMSeq getSprite() {

    return Utils.copy(sprite);
  }

  public VDMSeq getOriginalSprite() {

    return Utils.copy(originalTiles);
  }

  public Coordinate getTranslation() {

    return Utils.copy(translation);
  }

  public void setTranslation(final Number x, final Number y) {

    translation.x = x;
    translation.y = y;
    sprite = translateSprite(Utils.copy(sprite), translation.x, translation.y);
  }

  public void move(final Number x, final Number y) {

    translation.x = translation.x.longValue() + x.longValue();
    translation.y = translation.y.longValue() + y.longValue();
    sprite = translateSprite(Utils.copy(sprite), x, y);
  }

  protected VDMSeq translateSprite(final VDMSeq sp, final Number x, final Number y) {

    VDMSeq newSp = Utils.copy(sp);
    long toVar_1 = sp.size();

    for (Long inc = 1L; inc <= toVar_1; inc++) {
      ((Coordinate) Utils.get(newSp, inc)).x =
          ((Coordinate) Utils.get(sp, inc)).x.longValue() + x.longValue();
      ((Coordinate) Utils.get(newSp, inc)).y =
          ((Coordinate) Utils.get(sp, inc)).y.longValue() + y.longValue();
    }
    return Utils.copy(newSp);
  }

  public Character getSymbol() {

    return symbol;
  }

  public void rotate() {

    return;
  }

  public Number getRotation() {

    return 1L;
  }

  public void setRotation(final Number rot) {

    return;
  }

  public Piece() {}

  public String toString() {

    return "Piece{"
        + "DEFAULT_SPRITES = "
        + Utils.toString(DEFAULT_SPRITES)
        + ", translation := "
        + Utils.toString(translation)
        + ", originalTiles := "
        + Utils.toString(originalTiles)
        + ", sprite := "
        + Utils.toString(sprite)
        + ", symbol := "
        + Utils.toString(symbol)
        + "}";
  }

  public static class Coordinate implements Record {
    public Number x;
    public Number y;

    public Coordinate(final Number _x, final Number _y) {

      x = _x;
      y = _y;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Coordinate)) {
        return false;
      }

      Coordinate other = ((Coordinate) obj);

      return (Utils.equals(x, other.x)) && (Utils.equals(y, other.y));
    }

    public int hashCode() {

      return Utils.hashCode(x, y);
    }

    public Coordinate copy() {

      return new Coordinate(x, y);
    }

    public String toString() {

      return "mk_Piece`Coordinate" + Utils.formatFields(x, y);
    }
  }
}
