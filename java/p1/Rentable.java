public interface Rentable {
  void rent(User user) throws NoAvailableResourceException;

  User getUser();
}
