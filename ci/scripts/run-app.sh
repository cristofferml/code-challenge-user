printf "\nBuild app\n" \
&& sh ci/scripts/app-build.sh \
&& printf "\n\Creating docker images \n" \
&& sh ci/scripts/app-create-docker-image.sh \
&& printf "\nStarting docker compose\n" \
&& sh ci/scripts/start-compose.sh