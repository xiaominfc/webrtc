#/bin/sh

src_path=$(pwd)
export GOPATH=$GOPATH:$src_path
echo $GOPATH
go get -u golang.org/x/net
go build collidermain

