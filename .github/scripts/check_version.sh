#!/bin/sh

tag=$1
version=`grep "^version" gradle.properties | cut -d= -f2 | tr -d "[:space:]"`

if [ "$tag" != "" ]; then
  if [ "$tag" != "$version" ]; then
    echo "ERROR: the tag '$tag' is different from the version '$version' in the 'gradle.properties' file"
    exit 1
  fi
fi

git fetch --tags

if [ $(git tag -l "$version") ]; then
  echo "ERROR: version '$version' has already been released"
  exit 1
fi



