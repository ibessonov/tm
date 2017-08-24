package ibessonov.tm.user;

import ibessonov.tm.user.User.UserBuilder.E;
import ibessonov.tm.user.User.UserBuilder.LHash;
import ibessonov.tm.user.User.UserBuilder.ML;
import ibessonov.tm.user.User.UserBuilder.N;

import java.util.function.Consumer;

public class User {

    public static void main(String[] args) {
        User user = build(user()
                .withFirstName("John")
                .withLastName("Doe")
                .please()
        );
        System.out.println(user.getFirstName() + " " + user.getLastName());
    }

    private final String firstName;
    private final String lastName;

    private User(UserBuilder<?> builder) {
        this.firstName = builder.firstName;
        this.lastName  = builder.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static User build(E<? super E<? super User>> e) {
        User[] ref = { null };
        E<? super User> callback = user -> ref[0] = user;
        e.accept(callback);
        return ref[0];
    }

    public static UserBuilder<ML<? super N<? super LHash<? super N<? super E<? super E<? super User>>>>>>> user() {
        return new UserBuilder<>();
    }

    public static class UserBuilder<X> {

        private String firstName;
        private String lastName;

        @SuppressWarnings("unchecked")
        public QWRStart<? super LHash<? super N<? super X>>> please() {
            return (QWRStart) o -> {
                E<? super User> e = (E<? super User>) o;
                e.accept(new User(this));
            };
        }

        public UserBuilder<LFirstName<? super N<? super X>>> withFirstName(String firstName) {
            this.firstName = firstName;
            return cast(this);
        }

        public UserBuilder<LLastName<? super N<? super X>>> withLastName(String lastName) {
            this.lastName = lastName;
            return cast(this);
        }

        @SuppressWarnings("unchecked")
        private static <T> UserBuilder<T> cast(UserBuilder<?> builder) {
            return (UserBuilder<T>) builder;
        }

        public interface N<X> {}
        public interface ML<X> {}
        public interface MR<X> {}

        public interface LFirstName<X> {}
        public interface LLastName<X> {}
        public interface LHash<X> {}

        public interface E<X> extends
                Consumer<X>,
                QLRStart<N<? super QWRStart<? super E<? super E<? super X>>>>>,
                QRLStart<N<? super QWLStart<? super E<? super E<? super X>>>>>,
                QLRFirst<N<? super QWRFirst<? super E<? super E<? super X>>>>>,
                QRLFirst<N<? super QWLFirst<? super E<? super E<? super X>>>>>,
                QLRLast<N<? super QWRLast<? super E<? super E<? super X>>>>>,
                QRLLast<N<? super QWLLast<? super E<? super E<? super X>>>>>,
                QLRFinish<N<? super QWRFinish<? super E<? super E<? super X>>>>>,
                QRLFinish<N<? super QWLFinish<? super E<? super E<? super X>>>>>,
                QLRHalt<N<? super QWRHalt<? super E<? super E<? super X>>>>>,
                QRLHalt<N<? super QWLHalt<? super E<? super E<? super X>>>>> {}

        public interface QLRStart<X> {}
        public interface QWLStart<X> extends
                ML<N<? super QLStart<? super X>>>,
                MR<N<? super QWLStart<? super MR<? super N<? super X>>>>>,
                LFirstName<N<? super QWLStart<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWLStart<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWLStart<? super LHash<? super N<? super X>>>>>,
                E<QLRStart<? super N<? super X>>> {}

        public interface QRLStart<X> {}
        public interface QWRStart<X> extends
                MR<N<? super QRStart<? super X>>>,
                ML<N<? super QWRStart<? super ML<? super N<? super X>>>>>,
                LFirstName<N<? super QWRStart<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWRStart<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWRStart<? super LHash<? super N<? super X>>>>>,
                E<QRLStart<? super N<? super X>>> {}

        public interface QLRFirst<X> {}
        public interface QWLFirst<X> extends
                ML<N<? super QLFirst<? super X>>>,
                MR<N<? super QWLFirst<? super MR<? super N<? super X>>>>>,
                LFirstName<N<? super QWLFirst<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWLFirst<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWLFirst<? super LHash<? super N<? super X>>>>>,
                E<QLRFirst<? super N<? super X>>> {}

        public interface QRLFirst<X> {}
        public interface QWRFirst<X> extends
                MR<N<? super QRFirst<? super X>>>,
                ML<N<? super QWRFirst<? super ML<? super N<? super X>>>>>,
                LFirstName<N<? super QWRFirst<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWRFirst<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWRFirst<? super LHash<? super N<? super X>>>>>,
                E<QRLFirst<? super N<? super X>>> {}

        public interface QLRLast<X> {}
        public interface QWLLast<X> extends
                ML<N<? super QLLast<? super X>>>,
                MR<N<? super QWLLast<? super MR<? super N<? super X>>>>>,
                LFirstName<N<? super QWLLast<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWLLast<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWLLast<? super LHash<? super N<? super X>>>>>,
                E<QLRLast<? super N<? super X>>> {}

        public interface QRLLast<X> {}
        public interface QWRLast<X> extends
                MR<N<? super QRLast<? super X>>>,
                ML<N<? super QWRLast<? super ML<? super N<? super X>>>>>,
                LFirstName<N<? super QWRLast<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWRLast<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWRLast<? super LHash<? super N<? super X>>>>>,
                E<QRLLast<? super N<? super X>>> {}

        public interface QLRFinish<X> {}
        public interface QWLFinish<X> extends
                ML<N<? super QLFinish<? super X>>>,
                MR<N<? super QWLFinish<? super MR<? super N<? super X>>>>>,
                LFirstName<N<? super QWLFinish<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWLFinish<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWLFinish<? super LHash<? super N<? super X>>>>>,
                E<QLRFinish<? super N<? super X>>> {}

        public interface QRLFinish<X> {}
        public interface QWRFinish<X> extends
                MR<N<? super QRFinish<? super X>>>,
                ML<N<? super QWRFinish<? super ML<? super N<? super X>>>>>,
                LFirstName<N<? super QWRFinish<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWRFinish<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWRFinish<? super LHash<? super N<? super X>>>>>,
                E<QRLFinish<? super N<? super X>>> {}

        public interface QLRHalt<X> {}
        public interface QWLHalt<X> extends
                ML<N<? super QLHalt<? super X>>>,
                MR<N<? super QWLHalt<? super MR<? super N<? super X>>>>>,
                LFirstName<N<? super QWLHalt<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWLHalt<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWLHalt<? super LHash<? super N<? super X>>>>>,
                E<E<? super User>> {}

        public interface QRLHalt<X> {}
        public interface QWRHalt<X> extends
                MR<N<? super QRHalt<? super X>>>,
                ML<N<? super QWRHalt<? super ML<? super N<? super X>>>>>,
                LFirstName<N<? super QWRHalt<? super LFirstName<? super N<? super X>>>>>,
                LLastName<N<? super QWRHalt<? super LLastName<? super N<? super X>>>>>,
                LHash<N<? super QWRHalt<? super LHash<? super N<? super X>>>>>,
                E<E<? super User>> {}

        public interface QLStart<X> extends
                LHash<N<? super QWLFirst<? super LHash<? super N<? super LHash<? super N<? super MR<? super N<? super X>>>>>>>>> {}
        public interface QRStart<X> extends
                LHash<N<? super QWRFirst<? super LHash<? super N<? super MR<? super N<? super LHash<? super N<? super X>>>>>>>>> {}

        public interface QLFirst<X> extends
                LFirstName<N<? super QWLLast<? super LFirstName<? super N<? super MR<? super N<? super X>>>>>>> {}
        public interface QRFirst<X> extends
                LFirstName<N<? super QWRLast<? super MR<? super N<? super LFirstName<? super N<? super X>>>>>>> {}

        public interface QLLast<X> extends
                LLastName<N<? super QWLFinish<? super LLastName<? super N<? super MR<? super N<? super X>>>>>>> {}
        public interface QRLast<X> extends
                LLastName<N<? super QWRFinish<? super MR<? super N<? super LLastName<? super N<? super X>>>>>>> {}

        public interface QLFinish<X> extends
                LHash<N<? super QWLHalt<? super LHash<? super N<? super MR<? super N<? super LHash<? super N<? super X>>>>>>>>> {}
        public interface QRFinish<X> extends
                LHash<N<? super QWRHalt<? super LHash<? super N<? super ML<? super N<? super LHash<? super N<? super X>>>>>>>>> {}

        public interface QLHalt<X> {}
        public interface QRHalt<X> {}
    }
}
