import java.time.LocalDate;

class ScientificJournal extends Journal implements Scientific {
  String domain;

  public ScientificJournal(
      String title, String publisher, int resourceId, LocalDate publishDate, String domain) {
    super(title, publisher, resourceId, publishDate);
    this.domain = domain;
  }

  @Override
  public void print() {
    System.out.println("Scientific Journal: " + title + ", Domain: " + domain);
  }

  @Override
  public String getDomain() {
    return domain;
  }

  public boolean isTheSameDomain(Scientific res2) {
    return this.domain.equals(res2.getDomain());
  }
}
