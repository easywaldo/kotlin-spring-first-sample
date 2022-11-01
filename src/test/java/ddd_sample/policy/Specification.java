package ddd_sample.policy;

public interface Specification<T> {
    boolean isSatisfiedBy(T t);
    Specification<T> and(Specification<T> specification);
}
