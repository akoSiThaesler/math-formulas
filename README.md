# math-formulas

Quarkus REST service that computes area and perimeter of 2D shapes. The base project with the rectangle comes from the lecture; group D adds the triangle.

## Build and run with Docker only

No local JDK or Maven needed. The build stage compiles the code and runs every test whenever `pom.xml` or `src/` changed, and a failing test fails the build. On unchanged sources Docker reuses the cached layer; `docker build --no-cache` forces a re-run.

```bash
docker build -t math-formulas:v1.1 .
docker run -i --rm -p 8888:8888 math-formulas:v1.1
```

| URL | What |
|---|---|
| http://localhost:8888/q/swagger-ui | Swagger UI; click *Authorize* and log in with the demo user from `application.properties` |
| http://localhost:8888/q/openapi | OpenAPI document |
| http://localhost:8888/q/health | Health check |

## Endpoints

All endpoints need HTTP Basic with the role `restUser`.

| Method | Path | Body | Result |
|---|---|---|---|
| POST | `/v1/triangle` | `{"a": 3.0, "b": 4.0, "c": 5.0}` | `area` and `perimeter` |
| POST | `/v1/triangle/area` | same | `area` |
| POST | `/v1/triangle/perimeter` | same | `perimeter` |
| GET | `/v1/triangle/doc` | none | input description |

The rectangle has the same four endpoints under `/v1/rectangle` with the body `{"a": 10.0, "b": 5.0}`.

Invalid input returns HTTP 200 without values and with `messageOutputList`:

| Message | Cause |
|---|---|
| `TriangleInput.<a/b/c>.isNull` | side missing |
| `TriangleInput.<a/b/c>.isNegativeOrZero` | side is 0 or negative |
| `TriangleInput.sides.isInvalid` | triangle inequality violated (one side at least as long as the other two together) |
| `TriangleInput.isNull` | no request body |

The area uses Heron's formula in Kahan's numerically stable form (`Formulas.calculateTriangleArea`).

## Build with a local JDK 21

```bash
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t math-formulas-jvm .
```

On Windows use `mvnw.cmd package`.

## Upload to the course registry

One person per group uploads the image; the group namespace and login are announced in the lecture.

```bash
docker login container-registry.gugel.dev
docker tag math-formulas:v1.1 container-registry.gugel.dev/<namespace>/math-formulas:v1.1
docker push container-registry.gugel.dev/<namespace>/math-formulas:v1.1
```
