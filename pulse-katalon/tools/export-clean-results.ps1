param(
    [string]$ReportsDir = (Join-Path $PSScriptRoot "..\Reports"),
    [string]$OutputPath = (Join-Path $PSScriptRoot "..\Reports\katalon-test-results.csv")
)

$ErrorActionPreference = "Stop"

$rows = New-Object System.Collections.Generic.List[object]
$xmlFiles = Get-ChildItem -Path $ReportsDir -Recurse -Filter *.xml -ErrorAction SilentlyContinue

foreach ($file in $xmlFiles) {
    try {
        [xml]$xml = Get-Content -Raw -Path $file.FullName
    } catch {
        continue
    }

    $testCases = $xml.SelectNodes("//testcase")
    foreach ($testCase in $testCases) {
        $name = [string]$testCase.name
        if ($name -notmatch "(TC-\d{3})") {
            continue
        }

        $status = if ($testCase.failure -or $testCase.error) { "FAIL" } else { "PASS" }
        $duration = if ($testCase.time) { "$($testCase.time)s" } else { "" }

        $rows.Add([pscustomobject]@{
            "ID Bài Test" = $Matches[1]
            "Trạng thái Pass/Fail" = $status
            "Thời gian chạy" = $duration
        })
    }
}

$outputDir = Split-Path -Parent $OutputPath
New-Item -ItemType Directory -Force $outputDir | Out-Null

$rows |
    Sort-Object "ID Bài Test" -Unique |
    Export-Csv -Path $OutputPath -NoTypeInformation -Encoding UTF8

Write-Host "Clean CSV exported to $OutputPath"
