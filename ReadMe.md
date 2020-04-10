## DaggerDemo

This demo is from [dagger official tutorial](https://dagger.dev/tutorial/)

> # CONCEPTS
> - `@Component` tells Dagger to implement an interface or abstract class that creates and returns one or more application objects.
>   - Dagger will generate a class that implements the component type. The generated type will be named DaggerYourType (or DaggerYourType_NestedType for nested types)
> - `@Inject` on a constructor tells Dagger how to instantiate that class. We’ll see more shortly.