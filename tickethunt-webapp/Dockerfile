FROM node:14.17.0-alpine as build-step
RUN mkdir -p /webapp
WORKDIR /webapp
COPY package.json /webapp
RUN npm install
COPY . /webapp
RUN npm run build --prod
