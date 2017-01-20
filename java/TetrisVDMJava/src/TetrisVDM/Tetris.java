package TetrisVDM;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Tetris {
  private static final Character EMPTY_TILE = ' ';
  private static final VDMSeq PIECE_TYPES =
      SeqUtil.seq(
          TetrisVDM.quotes.IQuote.getInstance(),
          TetrisVDM.quotes.JQuote.getInstance(),
          TetrisVDM.quotes.LQuote.getInstance(),
          TetrisVDM.quotes.OQuote.getInstance(),
          TetrisVDM.quotes.SQuote.getInstance(),
          TetrisVDM.quotes.TQuote.getInstance(),
          TetrisVDM.quotes.ZQuote.getInstance());
  private static final VDMSeq PIECE_SYMBOLS = SeqUtil.seq('I', 'J', 'L', 'O', 'S', 'T', 'Z');
  private Boolean finished;
  private Number linesCleared;
  private Number rows;
  private Number columns;
  private VDMSeq table;
  private Piece currentPiece;
  private Piece nextPiece;
  private Boolean beginning;

  public void cg_init_Tetris_1(final Number r, final Number c) {

    beginning = true;
    finished = false;
    linesCleared = 0L;
    rows = r;
    columns = c;
    table = SeqUtil.seq();
    long toVar_4 = rows.longValue();

    for (Long inc1 = 1L; inc1 <= toVar_4; inc1++) {
      VDMSeq tempRow = SeqUtil.seq();
      long toVar_3 = columns.longValue();

      for (Long inc2 = 1L; inc2 <= toVar_3; inc2++) {
        tempRow = SeqUtil.conc(Utils.copy(tempRow), SeqUtil.seq(Tetris.EMPTY_TILE));
      }
      table = SeqUtil.conc(Utils.copy(table), SeqUtil.seq(Utils.copy(tempRow)));
    }
    return;
  }

  public Tetris(final Number r, final Number c) {

    cg_init_Tetris_1(r, c);
  }

  private void setRandomPiece() {

    Number rand = MATH.rand(Tetris.PIECE_TYPES.size()).longValue() + 1L;
    Number randNext = MATH.rand(Tetris.PIECE_TYPES.size()).longValue() + 1L;
    Number randRot = MATH.rand(4L).longValue() + 1L;
    VDMSeq positions = null;
    if (beginning) {
      currentPiece =
          Piece.createNewPiece(
              ((Object) Utils.get(PIECE_TYPES, rand)),
              ((Character) Utils.get(PIECE_SYMBOLS, rand)));
      nextPiece =
          Piece.createNewPiece(
              ((Object) Utils.get(PIECE_TYPES, randNext)),
              ((Character) Utils.get(PIECE_SYMBOLS, randNext)));
      beginning = false;

    } else {
      currentPiece = nextPiece;
      nextPiece =
          Piece.createNewPiece(
              ((Object) Utils.get(PIECE_TYPES, randNext)),
              ((Character) Utils.get(PIECE_SYMBOLS, randNext)));
    }

    long toVar_5 = randRot.longValue();

    for (Long inc = 1L; inc <= toVar_5; inc++) {
      currentPiece.rotate();
    }
    positions = getCurrentPieceHorizontalInitialPositions();
    currentPiece.move(
        ((Number) Utils.get(positions, MATH.rand(positions.size()).longValue() + 1L)), 0L);
    if (checkOverlap()) {
      finished = true;
    }

    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = checkBoardCollisionInY();
      if (!(whileCond_1)) {
        break;
      }

      {
        currentPiece.move(0L, 1L);
      }
    }

    Boolean orResult_7 = false;

    if (checkOverlap()) {
      orResult_7 = true;
    } else {
      orResult_7 = checkDownCollision(currentPiece.getSprite());
    }

    if (orResult_7) {
      finished = true;
      Boolean whileCond_2 = true;
      while (whileCond_2) {
        whileCond_2 = checkOverlap();
        if (!(whileCond_2)) {
          break;
        }

        currentPiece.move(0L, -1L);
      }
    }

    paintCurrentPiece();
  }

  public Boolean isGameFinished() {

    return finished;
  }

  public Piece getCurrentPiece() {

    return currentPiece;
  }

  public Piece getNextPiece() {

    return nextPiece;
  }

  public Character getTile(final Number x, final Number y) {

    return ((Character) Utils.get(((VDMSeq) Utils.get(table, y)), x));
  }

  public Number getLinesCleared() {

    return linesCleared;
  }

  public void init() {

    setRandomPiece();
  }

  public void next() {

    if (checkDownCollision(currentPiece.getSprite())) {
      checkLines();
      setRandomPiece();
    }
  }

  private void moveCurrentPiece(final Number x, final Number y) {

    removeCurrentPiece();
    currentPiece.move(x, y);
    paintCurrentPiece();
  }

  public void moveCurrentPieceDown() {

    if (!(checkDownCollision(currentPiece.getSprite()))) {
      moveCurrentPiece(0L, 1L);

    } else {
      next();
    }
  }

  public void moveCurrentPieceUp() {

    moveCurrentPiece(0L, -1L);
  }

  public void moveCurrentPieceLeft() {

    if (!(checkLeftCollision(currentPiece.getSprite()))) {
      moveCurrentPiece(-1L, 0L);
    }
  }

  public void moveCurrentPieceRight() {

    if (!(checkRightCollision(currentPiece.getSprite()))) {
      moveCurrentPiece(1L, 0L);
    }
  }

  public void rotate() {

    removeCurrentPiece();
    rotateCurrentPiece();
  }

  private void rotateCurrentPiece() {

    Piece.Coordinate originalTranslation = currentPiece.getTranslation();
    Number originalRot = currentPiece.getRotation();
    Boolean valid = false;
    Tuple boardCheck = null;
    currentPiece.rotate();
    Boolean whileCond_3 = true;
    while (whileCond_3) {
      whileCond_3 = !(valid);
      if (!(whileCond_3)) {
        break;
      }

      {
        boardCheck = checkBoardOverlap();
        if (Utils.equals(boardCheck, Tuple.mk_(false, false, false, false))) {
          valid = true;

        } else {
          if (((Boolean) boardCheck.get(0))) {
            currentPiece.move(0L, 1L);
          }

          if (((Boolean) boardCheck.get(1))) {
            currentPiece.move(0L, -1L);
          }

          if (((Boolean) boardCheck.get(2))) {
            currentPiece.move(1L, 0L);
          }

          if (((Boolean) boardCheck.get(3))) {
            currentPiece.move(-1L, 0L);
          }
        }
      }
    }

    if (checkOverlap()) {
      currentPiece.setRotation(originalRot);
      currentPiece.setTranslation(originalTranslation.x, originalTranslation.y);
    }

    paintCurrentPiece();
  }

  public Tuple checkBoardOverlap() {

    Boolean up = false;
    Boolean down = false;
    Boolean left = false;
    Boolean right = false;
    for (Iterator iterator_8 = currentPiece.getSprite().iterator(); iterator_8.hasNext(); ) {
      Piece.Coordinate tile = (Piece.Coordinate) iterator_8.next();
      {
        if (tile.y.longValue() < 1L) {
          up = true;
        }

        if (tile.x.longValue() < 1L) {
          left = true;
        }

        if (tile.x.longValue() > columns.longValue()) {
          right = true;
        }

        if (tile.y.longValue() > rows.longValue()) {
          down = true;
        }
      }
    }
    return Tuple.mk_(up, down, left, right);
  }

  public Boolean checkOverlap() {

    for (Iterator iterator_9 = currentPiece.getSprite().iterator(); iterator_9.hasNext(); ) {
      Piece.Coordinate tile = (Piece.Coordinate) iterator_9.next();
      Boolean andResult_31 = false;

      if (tile.y.longValue() > 0L) {
        Boolean andResult_32 = false;

        if (tile.y.longValue() <= rows.longValue()) {
          Boolean andResult_33 = false;

          if (tile.x.longValue() > 0L) {
            if (tile.x.longValue() <= columns.longValue()) {
              andResult_33 = true;
            }
          }

          if (andResult_33) {
            andResult_32 = true;
          }
        }

        if (andResult_32) {
          andResult_31 = true;
        }
      }

      if (andResult_31) {
        if (!(Utils.equals(
            ((Character) Utils.get(((VDMSeq) Utils.get(table, tile.y)), tile.x)), ' '))) {
          return true;
        }
      }
    }
    return false;
  }

  public Boolean checkDownCollision(final VDMSeq sp) {

    for (Iterator iterator_10 = sp.iterator(); iterator_10.hasNext(); ) {
      Piece.Coordinate tile = (Piece.Coordinate) iterator_10.next();
      Boolean andResult_34 = false;

      if (!(Utils.equals(tile.y, rows))) {
        if (tile.y.longValue() > 0L) {
          andResult_34 = true;
        }
      }

      if (andResult_34) {
        Boolean andResult_35 = false;

        if (!(Utils.equals(
            ((Character) Utils.get(((VDMSeq) Utils.get(table, tile.y.longValue() + 1L)), tile.x)),
            ' '))) {
          if (!(SetUtil.inSet(
              new Piece.Coordinate(tile.x, tile.y.longValue() + 1L),
              SeqUtil.elems(Utils.copy(sp))))) {
            andResult_35 = true;
          }
        }

        if (andResult_35) {
          return true;
        }

      } else {
        return true;
      }
    }
    return false;
  }

  public Boolean checkRightCollision(final VDMSeq sp) {

    for (Iterator iterator_11 = sp.iterator(); iterator_11.hasNext(); ) {
      Piece.Coordinate tile = (Piece.Coordinate) iterator_11.next();
      {
        if (!(Utils.equals(tile.x, columns))) {
          Boolean andResult_36 = false;

          if (!(Utils.equals(
              ((Character) Utils.get(((VDMSeq) Utils.get(table, tile.y)), tile.x.longValue() + 1L)),
              ' '))) {
            if (!(SetUtil.inSet(
                new Piece.Coordinate(tile.x.longValue() + 1L, tile.y),
                SeqUtil.elems(Utils.copy(sp))))) {
              andResult_36 = true;
            }
          }

          if (andResult_36) {
            return true;
          }

        } else {
          return true;
        }
      }
    }
    return false;
  }

  public Boolean checkLeftCollision(final VDMSeq sp) {

    for (Iterator iterator_12 = sp.iterator(); iterator_12.hasNext(); ) {
      Piece.Coordinate tile = (Piece.Coordinate) iterator_12.next();
      {
        if (!(Utils.equals(tile.x, 1L))) {
          Boolean andResult_37 = false;

          if (!(Utils.equals(
              ((Character) Utils.get(((VDMSeq) Utils.get(table, tile.y)), tile.x.longValue() - 1L)),
              ' '))) {
            if (!(SetUtil.inSet(
                new Piece.Coordinate(tile.x.longValue() - 1L, tile.y),
                SeqUtil.elems(Utils.copy(sp))))) {
              andResult_37 = true;
            }
          }

          if (andResult_37) {
            return true;
          }

        } else {
          return true;
        }
      }
    }
    return false;
  }

  public void drop() {

    Boolean whileCond_4 = true;
    while (whileCond_4) {
      whileCond_4 = !(checkDownCollision(currentPiece.getSprite()));
      if (!(whileCond_4)) {
        break;
      }

      moveCurrentPiece(0L, 1L);
    }
  }

  private void checkLines() {

    VDMSeq seqCompResult_1 = SeqUtil.seq();
    VDMSeq set_4 = Utils.copy(table);
    for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext(); ) {
      VDMSeq r = ((VDMSeq) iterator_4.next());
      if (!(SetUtil.inSet(' ', SeqUtil.elems(Utils.copy(r))))) {
        seqCompResult_1.add(Utils.copy(r));
      }
    }
    VDMSeq completeRows = Utils.copy(seqCompResult_1);
    VDMSeq seqCompResult_2 = SeqUtil.seq();
    VDMSeq set_5 = Utils.copy(table);
    for (Iterator iterator_5 = set_5.iterator(); iterator_5.hasNext(); ) {
      VDMSeq r = ((VDMSeq) iterator_5.next());
      if (SetUtil.inSet(' ', SeqUtil.elems(Utils.copy(r)))) {
        seqCompResult_2.add(Utils.copy(r));
      }
    }
    VDMSeq incompleteRows = Utils.copy(seqCompResult_2);
    Number newLines = completeRows.size();
    clearTable();
    long toVar_6 = incompleteRows.size();

    for (Long inc = 1L; inc <= toVar_6; inc++) {
      Utils.mapSeqUpdate(
          table,
          inc.longValue() + newLines.longValue(),
          Utils.copy(((VDMSeq) Utils.get(incompleteRows, inc))));
    }
    linesCleared = linesCleared.longValue() + newLines.longValue();
  }

  public void paintCurrentPiece() {

    paint(currentPiece.getSprite(), currentPiece.getSymbol());
  }

  public void removeCurrentPiece() {

    paint(currentPiece.getSprite(), ' ');
  }

  private void paint(final VDMSeq positions, final Character symbol) {

    for (Iterator iterator_13 = positions.iterator(); iterator_13.hasNext(); ) {
      Piece.Coordinate pos = (Piece.Coordinate) iterator_13.next();
      Boolean andResult_38 = false;

      if (pos.y.longValue() > 0L) {
        Boolean andResult_39 = false;

        if (pos.y.longValue() <= rows.longValue()) {
          Boolean andResult_40 = false;

          if (pos.x.longValue() > 0L) {
            if (pos.x.longValue() <= columns.longValue()) {
              andResult_40 = true;
            }
          }

          if (andResult_40) {
            andResult_39 = true;
          }
        }

        if (andResult_39) {
          andResult_38 = true;
        }
      }

      if (andResult_38) {
        Utils.mapSeqUpdate(((VDMSeq) Utils.get(table, pos.y)), pos.x, symbol);
      }
    }
  }

  private VDMSeq getCurrentPieceHorizontalInitialPositions() {

    VDMSeq positions = SeqUtil.seq();
    Number inc = 1L;
    Tuple result = checkBoardCollisionInX(inc);
    Boolean whileCond_5 = true;
    while (whileCond_5) {
      whileCond_5 = !(((Boolean) result.get(1)));
      if (!(whileCond_5)) {
        break;
      }

      {
        if (!(((Boolean) result.get(0)))) {
          positions = SeqUtil.conc(Utils.copy(positions), SeqUtil.seq(inc));
        }

        inc = inc.longValue() + 1L;
        result = checkBoardCollisionInX(inc);
      }
    }

    return Utils.copy(positions);
  }

  private Tuple checkBoardCollisionInX(final Number x) {

    Boolean rightSideCollision = false;
    Boolean leftSideCollision = false;
    for (Iterator iterator_14 = currentPiece.getSprite().iterator(); iterator_14.hasNext(); ) {
      Piece.Coordinate t = (Piece.Coordinate) iterator_14.next();
      {
        if (t.x.longValue() + x.longValue() > columns.longValue()) {
          rightSideCollision = true;
        }

        if (t.x.longValue() + x.longValue() <= 0L) {
          leftSideCollision = true;
        }
      }
    }
    return Tuple.mk_(leftSideCollision, rightSideCollision);
  }

  private Boolean checkBoardCollisionInY() {

    for (Iterator iterator_15 = currentPiece.getSprite().iterator(); iterator_15.hasNext(); ) {
      Piece.Coordinate t = (Piece.Coordinate) iterator_15.next();
      {
        if (t.y.longValue() <= 0L) {
          return true;
        }
      }
    }
    return false;
  }

  private void clearTable() {

    long toVar_8 = rows.longValue();

    for (Long y = 1L; y <= toVar_8; y++) {
      long toVar_7 = columns.longValue();

      for (Long x = 1L; x <= toVar_7; x++) {
        Utils.mapSeqUpdate(((VDMSeq) Utils.get(table, y)), x, ' ');
      }
    }
  }

  public void print() {

    IO.print(SeqUtil.seq('#'));
    long toVar_9 = columns.longValue();

    for (Long inc = 1L; inc <= toVar_9; inc++) {
      IO.print(SeqUtil.seq('#'));
    }
    IO.println(SeqUtil.seq('#'));
    for (Iterator iterator_16 = table.iterator(); iterator_16.hasNext(); ) {
      VDMSeq e = (VDMSeq) iterator_16.next();
      {
        IO.print(SeqUtil.seq('#'));
        IO.print(Utils.copy(e));
        IO.println(SeqUtil.seq('#'));
      }
    }
    IO.print(SeqUtil.seq('#'));
    long toVar_10 = columns.longValue();

    for (Long inc = 1L; inc <= toVar_10; inc++) {
      IO.print(SeqUtil.seq('#'));
    }
    IO.println(SeqUtil.seq('#'));
  }

  public Tetris() {}

  public String toString() {

    return "Tetris{"
        + "EMPTY_TILE = "
        + Utils.toString(EMPTY_TILE)
        + ", PIECE_TYPES = "
        + Utils.toString(PIECE_TYPES)
        + ", PIECE_SYMBOLS = "
        + Utils.toString(PIECE_SYMBOLS)
        + ", finished := "
        + Utils.toString(finished)
        + ", linesCleared := "
        + Utils.toString(linesCleared)
        + ", rows := "
        + Utils.toString(rows)
        + ", columns := "
        + Utils.toString(columns)
        + ", table := "
        + Utils.toString(table)
        + ", currentPiece := "
        + Utils.toString(currentPiece)
        + ", nextPiece := "
        + Utils.toString(nextPiece)
        + ", beginning := "
        + Utils.toString(beginning)
        + "}";
  }
}
