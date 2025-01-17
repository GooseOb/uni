class ScientificBook extends PaperBook implements Scientific {
  String domain;

  public ScientificBook(
      String title, String publisher, int resourceId, String author, int pages, String domain) {
    super(title, publisher, resourceId, author, pages);
    this.domain = domain;
  }

  @Override
  public void print() {
    System.out.println("ScientificBook: " + title + " by " + author + ", Domain: " + domain);
  }

  @Override
  public String getDomain() {
    return domain;
  }

  public boolean isTheSameDomain(Scientific res2) {
    return this.domain.equals(res2.getDomain());
  }
}
