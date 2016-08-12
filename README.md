# Kotlin Coroutines
1. [Overview](#overview)
1. [Building](#building)
1. [Configurations](#configurations)
    1. [Release](#release)
1. [Testing](#testing)
    1. [Unit Tests](#unit-tests)
    1. [UI/Instrumentation Tests](#uiinstrumentation-tests)
    1. [Code Coverage](#code-coverage)
1. [Architecture](#architecture)
    1. [Model-View-Presenter](#model-view-presenter)
    1. [Base Classes](#base-classes)

## Overview
TODO

## Building
This project does not require any additional setup or special configurations to build or run.

## Testing
### Unit Tests
Unit tests exist under the "test" directory. They can be run using the standard commands. ex. `./gradlew testDebugUnitTest`

### UI/Instrumentation Tests
UI (Espresso) tests exist under the "androidTest" directory. The project also uses [Spoon](https://github.com/square/spoon) and its [gradle plugin](https://github.com/stanfy/spoon-gradle-plugin) to run instrumentation tests and generate a easy to read report. To run an instrumentation test, use `./gradlew spoon`.

### Code Coverage
Code coverage configuration are handled by [coverage.gradle](app/coverage.gradle). To generate a code coverage report, use `./gradlew testCoverage`. This will run both unit and instrumentation tests and merge the result of both tests into a single report.

## Architecture
### Model-View-Presenter
The app uses the popular MVP architecture to allow for separation of logic and ease of testing. In this paradigm, all business logic should live inside presenters (but they can delegate some tasks to other classes that are injected as dependencies). Activities and fragment will act as "views", they should not have any logic other than passing the user events to the presenter and displaying the data. There are also Contract classes that specify the communication interface between the views and presenters.

### Base Classes
- `BaseActivity`: Base class for all activities. Includes lifecycle logging, view inflation, MVP.
- `BaseFragmentActivity`: Base class for activities whose sole purpose to to host a fragment. If the activity contains any additional logic, use `BaseMvpActivity` instead.
- `BaseFragment`: Basically the same as `BaseActivity`, but for fragments.
- `BasePresenter`: Base class for all presenters. Includes lifecycle setup and common dependencies. Goes along with `BaseMvpActivity` and `BaseFragment`.
- `BaseScreen`: An interface that all "views" (Activity/Fragment/Service) should implement.
- `PresenterConfiguration`: Wrapper class for common dependencies that all presenters are expected to have.