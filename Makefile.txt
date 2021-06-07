help::
	@grep '^[^#[:space:]]*::\?.*#.*' Makefile | GREP_COLOR='01;34' grep --color=always '^[^:]*' | GREP_COLOR='01;36' grep --color '[^#]*'

compile-java::
	cd java && \
	./gradlew build test install