#!/bin/bash -x

function deploy() {
    local api_point=$1;
    local org_name=$2;
    local space_name=$3;
    local user_name=$4;
    local user_password=$5;
    local mtar_file=$6;
    local extension_file=$7;

    cf login -a "$api_point" -o "$org_name" -s "$space_name" -u "$user_name" -p "$user_password"

    cf deploy "$mtar_file" -e "$extension_file" -f --skip-testing-phase --abort-on-error --version-rule ALL

}

cd ../

deploy "$1" "$2" "$3" "$4" "$5" "$6" "$7"




