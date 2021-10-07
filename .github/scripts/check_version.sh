#!/bin/sh

tag=$1
version=`grep "^version" gradle.properties | cut -d= -f2 | tr -d "[:space:]"`

if [ "$tag" != "" ]; then
  if [ "$tag" != "$version" ]; then
    echo "ERROR: The tag '$tag' is different from the version '$version' in the 'gradle.properties' file."
    exit 1
  fi
fi

git fetch --tags

echo "1:$version"
echo "2:`git tag -l "$version"`"
echo "3:$(git tag -l "$version")"

if [ $(git tag -l "$version") ]; then
  echo "ERROR: Version '$version' has already been released."
  exit 1
fi



