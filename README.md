# Sample application modular architecture

This project is MVVM/MVI based with a unidirectional flow. A single data class stored in the
ViewModel is used to represent a view state. The ViewModel accepts actions to be performed
which are then 'transformed' to execute use-cases. The result from the use-cases are then 'reduced'
by updating the current state with the new state which is observed by the view.

The architecture leans on modularization where it makes sense using Kotlin, Dagger, Architecture components and Coroutines.

# Module architecture

**lib-*** - These are discrete pieces of functionality or 'horizontals' bundled as an Android library.

**feature-*** - These are complete features or 'verticals' that may rely on several libraries.

**base** - This contains base classes that may be used across libraries or features.

**app** - Contains the application class and defines the apps entry point and dependency graph.

# Package architecture

**datasource** - This is where data sources live, i.e. network, database, shared preferences, etc.

**repository** - Abstracts away data sources, allowing data sources to change easily without
consumers of the repository being affected. Caching strategies can also be defined here.

**usecase** - Contains use-cases which perform domain specific business logic.

**injection** - Contains dependency injection classes.

**model** - Contains model data classes.

**viewmodel** - Executes use-cases from actions as well as retains and exposes the view state.

**view** - Contains view logic, typically fragments/activities/services, etc.

**viewslice** - Contains discrete pieces of view logic which can be consumed by views (mainly useful when you need to re-use view logic with lifecycle).

**extension** - Contains extension classes.

# Naming convention standards

**Git Branches**
- **Features:** `feature/EXAMPLE-{ticket_number}_{short_description_of_feature}` i.e. *feature/EXAMPLE-1_hello_world*
- **Bug-fix:** `fix/EXAMPLE-{ticket_number}_{short_description_of_bug}` i.e. *fix/EXAMPLE-2_settings_crash*
- **Spike:** `spike/EXAMPLE-{ticket_number}_{short_description_of_spike}` i.e. *spike/EXAMPLE-3_dark_theme*

**XML resource ID's** - `{feature}_{component_type}_{description}` i.e. *settings_image_error*