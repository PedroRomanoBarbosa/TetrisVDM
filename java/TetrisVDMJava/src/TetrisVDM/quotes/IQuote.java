package TetrisVDM.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class IQuote {
  private static int hc = 0;
  private static IQuote instance = null;

  public IQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static IQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new IQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof IQuote;
  }

  public String toString() {

    return "<I>";
  }
}
