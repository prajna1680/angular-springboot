FROM node:6.9.1
RUN mkdir /src/app/docrestaurant
COPY dist /src/app/docrestaurant/dist
COPY deploy-package.json /src/app/docrestaurant/package.json
COPY deploy-server.js /src/app/docrestaurant
WORKDIR /src/app/docrestaurant
RUN npm install
EXPOSE 3000
CMD ["node", "deploy-server"]
