Command := $(firstword $(MAKECMDGOALS))
Arguments := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))

build:
	./mvnw clean install

.PHONY: run
run:
	java -jar ./target/expression-engine-0.0.1.jar "$(Arguments)"

%::
	@true
